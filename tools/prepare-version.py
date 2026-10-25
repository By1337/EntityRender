#!/usr/bin/env python3
"""Prepare an explicit version branch from local schemas and NMS contracts; never commits."""
import argparse
import json
import os
from pathlib import Path
import re
import subprocess
import zipfile

ROOT = Path(__file__).resolve().parents[1]


def run(*args, env=None):
    subprocess.run(args, cwd=ROOT, env=env, check=True)


def natural(value):
    return tuple((1, int(p)) if p.isdigit() else (0, p) for p in re.split(r"(\d+)", str(value)))


def parameters(text):
    result, start, depth = [], 0, 0
    for i, c in enumerate(text):
        if c == '<': depth += 1
        if c == '>': depth -= 1
        if c == ',' and depth == 0:
            result.append(text[start:i].strip())
            start = i + 1
    if text.strip(): result.append(text[start:].strip())
    return result


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('version')
    parser.add_argument('server', type=Path)
    parser.add_argument('--schema', type=Path)
    parser.add_argument('--allow-dirty', action='store_true')
    args = parser.parse_args()
    if not args.allow_dirty and subprocess.check_output(['git', 'status', '--porcelain'], cwd=ROOT).strip():
        raise SystemExit('Commit the preceding version before preparing another one')
    schema = (args.schema or ROOT / '.entityrender/schemas' / args.version).resolve()
    version = dict(line.split('\t') for line in (schema / 'version.tsv').read_text().splitlines()[1:])
    if version['minecraft'] != args.version:
        raise SystemExit('Schema version does not match requested version')
    server = args.server.resolve()
    candidates = list((Path.home() / '.m2/repository/ca/bkaw/paper-nms').glob(args.version + '*'))
    candidates = [p for p in candidates if list(p.glob('paper-nms-*.jar'))]
    sdk_dir = max(candidates, key=natural)
    sdk = next(p for p in sdk_dir.glob('paper-nms-*.jar') if not p.name.endswith('-sources.jar'))
    with zipfile.ZipFile(sdk) as jar:
        entries = set(jar.namelist())
        metadata = json.loads(jar.read('version.json'))
    release = int(metadata['java_version'])
    jdk = Path('/usr/lib/jvm') / f'java-{release}-openjdk'
    env = dict(os.environ, JAVA_HOME=str(jdk))
    api_dirs = list((server / 'libraries/io/papermc/paper/paper-api').iterdir())
    api_version = max(api_dirs, key=natural).name
    if api_version.endswith('-R0.1-SNAPSHOT'):
        api_version = args.version + '-R0.1-SNAPSHOT'

    def javap(name):
        return subprocess.check_output([str(jdk / 'bin/javap'), '-classpath', str(sdk), name], text=True)

    def replace(path, fn):
        path = ROOT / path
        path.write_text(fn(path.read_text()))

    def property_value(text, key, value):
        return re.sub(r'(<'+re.escape(key)+r'>)[^<]*(</'+re.escape(key)+r'>)', lambda m: m[1]+str(value)+m[2], text)

    with zipfile.ZipFile(next(server.glob('paper*.jar'))) as bundle:
        coordinates = [row.split('\t')[1].split(':') for row in bundle.read('META-INF/libraries.list').decode().splitlines() if row.strip()]
    server_dependencies = {artifact: version for group, artifact, version in coordinates}

    for file in ['pom.xml', 'api/pom.xml', 'core/pom.xml', 'dumper/pom.xml']:
        text = (ROOT / file).read_text()
        text = re.sub(r'2\.0\.0(?:\+[^<]+)?-SNAPSHOT', '2.0.0+'+args.version+'-SNAPSHOT', text)
        text = property_value(text, 'maven.compiler.release', 21 if file == 'dumper/pom.xml' else release)
        if file == 'pom.xml':
            for key, value in {'paper.version': api_version, 'paper.nms.version': sdk_dir.name,
                               'minecraft.version': args.version, 'minecraft.protocol': version['protocol'],
                               'netty.version': server_dependencies['netty-buffer'], 'datafixerupper.version': server_dependencies['datafixerupper']}.items():
                text = property_value(text, key, value)
        (ROOT / file).write_text(text)

    readme = ROOT / 'README.md'
    text = re.sub(r'Paper [0-9.]+ / Java [0-9]+', 'Paper '+args.version+' / Java '+str(release), readme.read_text())
    readme.write_text(text)

    core = ROOT / 'core/src'
    static_types = 'EntityTypes' if 'net/minecraft/world/entity/EntityTypes.class' in entries else 'EntityType'
    identifier = 'Identifier' if 'net/minecraft/resources/Identifier.class' in entries else 'ResourceLocation'
    rotations = javap('net.minecraft.core.Rotations')
    record_rotations = 'float x()' in rotations
    unsafe = subprocess.check_output([str(jdk / 'bin/javap'), '-classpath', str(next(max(api_dirs,key=natural).glob('*.jar'))), 'org.bukkit.UnsafeValues'], text=True)
    world_ids = 'nextEntityId(org.bukkit.World)' in unsafe
    for path in core.rglob('*.java'):
        if path.name.startswith('Generated'): continue
        text = path.read_text()
        text = re.sub(r'net\.minecraft\.world\.entity\.EntityTypes?\.(?=[A-Z_])', 'net.minecraft.world.entity.'+static_types+'.', text)
        text = re.sub(r'Bukkit\.getUnsafe\(\)\.nextEntityId\((?:world)?\)', 'Bukkit.getUnsafe().nextEntityId('+('world' if world_ids else '')+')', text)
        text = text.replace('net.minecraft.resources.ResourceLocation', 'net.minecraft.resources.'+identifier).replace('net.minecraft.resources.Identifier', 'net.minecraft.resources.'+identifier)
        path.write_text(text)
    replace('dumper/src/main/java/dev/by1337/entityrender/dumper/RuntimeGenerator.java', lambda s:
            re.sub(r'net\.minecraft\.resources\.(?:ResourceLocation|Identifier)', 'net.minecraft.resources.'+identifier, s))
    if 'net/minecraft/world/entity/EntityReference.class' in entries:
        factory = '::of' if ' of(java.util.UUID)' in javap('net.minecraft.world.entity.EntityReference') else '::new'
        replace('dumper/src/main/java/dev/by1337/entityrender/dumper/RuntimeGenerator.java', lambda s:
                re.sub(r'net\.minecraft\.world\.entity\.EntityReference::(?:new|of)', 'net.minecraft.world.entity.EntityReference'+factory, s))
    getter = 'x()' if record_rotations else 'getX()'
    replace('core/src/main/java/dev/by1337/entityrender/core/ArmorStandEntity.java', lambda s:
            re.sub(r'private EulerAngle pose\(MetadataKey<Rotations> key\) \{.*?\n    }',
                   'private EulerAngle pose(MetadataKey<Rotations> key) {\n        var value=get(key);\n        return new EulerAngle(Math.toRadians(value.'+getter+'),Math.toRadians(value.'+getter.replace('x','y').replace('X','Y')+'),Math.toRadians(value.'+getter.replace('x','z').replace('X','Z')+'));\n    }', s, flags=re.S))

    fields = [line.split('\t') for line in (schema/'fields.tsv').read_text().splitlines()[1:]]
    villager = next(row[4] for row in fields if row[3] == 'VILLAGER_DATA')
    villager_api = javap(villager)
    holders = 'net.minecraft.core.Holder' in next(line for line in villager_api.splitlines() if 'public '+villager+'(' in line)
    vget = ('type()', 'profession()', 'level()') if holders else ('getType()', 'getProfession()', 'getLevel()')
    converter = 'minecraftHolderToBukkit' if holders else 'minecraftToBukkit'
    reverse = ('holder(value.type(),Registries.VILLAGER_TYPE),holder(value.profession(),Registries.VILLAGER_PROFESSION)'
               if holders else 'CraftRegistry.bukkitToMinecraft(value.type()),CraftRegistry.bukkitToMinecraft(value.profession())')
    body = ('static VillagerAppearance villager(VillagerData value) { return new VillagerAppearance(CraftRegistry.'+converter+'(value.'+vget[0]+',Registries.VILLAGER_TYPE),CraftRegistry.'+converter+'(value.'+vget[1]+',Registries.VILLAGER_PROFESSION),value.'+vget[2]+'); }\n'
            '    static VillagerData villager(VillagerAppearance value) { Objects.requireNonNull(value); return new VillagerData('+reverse+',value.level()); }\n\n    ')
    tag_api = javap('net.minecraft.nbt.TagParser')
    modern_tag = 'parseCompoundFully(java.lang.String)' in tag_api
    holder_signature = next(line for line in javap('org.bukkit.craftbukkit.CraftRegistry').splitlines() if ' bukkitToMinecraftHolder(' in line)
    holder_registry = 'ResourceKey' in holder_signature
    def values(text):
        text = re.sub(r'return CraftRegistry\.bukkitToMinecraftHolder\(value(?:, registry)?\);', 'return CraftRegistry.bukkitToMinecraftHolder(value'+(', registry' if holder_registry else '')+');', text)
        text = re.sub(r'import net\.minecraft\.world\.entity\.[^;]*\.VillagerData;', 'import '+villager+';', text)
        text = re.sub(r'static VillagerAppearance villager\(.*?(?=static String compound)', body, text, flags=re.S)
        text = text.replace('TagParser.parseTag(', 'TagParser.parseCompoundFully(') if modern_tag else text.replace('TagParser.parseCompoundFully(', 'TagParser.parseTag(')
        if modern_tag:
            text = re.sub(r'private static Tag parseSnbt\(String text\).*?\n    }',
                          'private static Tag parseSnbt(String text) throws com.mojang.brigadier.exceptions.CommandSyntaxException { return TagParser.create(NbtOps.INSTANCE).parseFully(Objects.requireNonNull(text));\n    }',text,flags=re.S)
        return text
    replace('core/src/main/java/dev/by1337/entityrender/core/ValueConversions.java', values)

    constructor = next(line for line in javap('net.minecraft.server.level.ServerEntity').splitlines() if 'public net.minecraft.server.level.ServerEntity(' in line)
    args_native = parameters(constructor.split('(',1)[1].rsplit(')',1)[0])
    expression = ['level', 'prototype']
    for kind in args_native[2:]:
        if kind == 'int': expression.append('1')
        elif kind == 'boolean': expression.append('false')
        elif 'UpdateInterval' in kind: expression.append('net.minecraft.world.entity.UpdateInterval.periodic(1)')
        elif 'java.util.function.BiConsumer' in kind: expression.append('(packet,players) -> {}')
        elif 'java.util.function.Consumer' in kind: expression.append('packet -> {}')
        elif kind.startswith('java.util.Set'): expression.append('Set.of()')
        elif 'Synchronizer' in kind:
            methods = []
            for line in javap(kind).splitlines():
                if 'public abstract void ' not in line: continue
                signature = line.strip().removeprefix('public abstract void ').removesuffix(';')
                name, types = signature.split('(',1)
                params = ','.join(t.replace('$','.')+' p'+str(i) for i,t in enumerate(parameters(types[:-1])))
                methods.append('public void '+name+'('+params+') {}')
            expression.append('new '+kind.replace('$','.')+'() {'+' '.join(methods)+'}')
        else: raise RuntimeError('Unrecognized ServerEntity constructor argument: '+kind)
    has_orb = 'net/minecraft/network/protocol/game/ClientboundAddExperienceOrbPacket.class' in entries
    def renderer(text):
        text = re.sub(r'var tracker = new net\.minecraft\.server\.level\.ServerEntity\(.*?\);', 'var tracker = new net.minecraft.server.level.ServerEntity('+','.join(expression)+');',text,flags=re.S)
        if not has_orb:
            text = re.sub(r': packet instanceof net\.minecraft\.network\.protocol\.game\.ClientboundAddExperienceOrbPacket orb \? orb\.getValue\(\) : 0', ': 0',text)
        return text
    replace('core/src/main/java/dev/by1337/entityrender/core/PaperEntityRenderer.java', renderer)
    if not has_orb:
        replace('core/src/main/java/dev/by1337/entityrender/core/SpawnPackets.java', lambda s:
                re.sub(r'        if \(type == org\.bukkit\.entity\.EntityType\.EXPERIENCE_ORB\).*?(?=        return new ClientboundAddEntityPacket)', '',s,flags=re.S))
        replace('core/src/test/java/dev/by1337/entityrender/core/AllEntitiesChecks.java', lambda s:
                re.sub(r'\s*if \(type == EntityType\.EXPERIENCE_ORB.*?throw new AssertionError\("Orb requires its native spawn packet"\);','',s,flags=re.S)
                .replace('orb.setExperienceValue(17)', 'orb.setValue(17)'))
        replace('core/src/test/java/dev/by1337/entityrender/core/AllEntitiesChecks.java', lambda s:
                re.sub(r'if \(!\(packets.getFirst\(\) instanceof ClientboundAddExperienceOrbPacket xp\).*?throw new AssertionError\("Orb value was not encoded by the native spawn codec"\);',
                       'if (!(packets.getFirst() instanceof ClientboundAddEntityPacket)) throw new AssertionError("Missing native orb spawn packet");',s,flags=re.S))
    position_api = javap('net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket')
    path_position = 'PositionPath' in position_api
    def position(text):
        text = re.sub(r'import net\.minecraft\.world\.entity\.Position(?:MoveRotation|Path);','import net.minecraft.world.entity.'+('PositionPath' if path_position else 'PositionMoveRotation')+';',text)
        expression = ('PositionPath.of(new Vec3(x,y,z)),yaw,pitch,onGround' if path_position else 'new PositionMoveRotation(new Vec3(x,y,z),Vec3.ZERO,yaw,pitch),onGround')
        return re.sub(r'broadcast\(new ClientboundEntityPositionSyncPacket\(.*?onGround\)\);','broadcast(new ClientboundEntityPositionSyncPacket(id,'+expression+'));',text,flags=re.S)
    replace('core/src/main/java/dev/by1337/entityrender/core/BaseVirtualEntity.java',position)

    destination = ROOT/'dumper/src/test/resources/golden'/args.version
    destination.mkdir(parents=True, exist_ok=True)
    for file in schema.glob('*.tsv'):
        (destination/file.name).write_bytes(file.read_bytes())
    run('sh','generate-metadata.sh',str(destination),env=env)
    run('sh','generate-runtime.sh',str(destination),env=env)
    run('java-format','--diff',env=env)
    run('git','diff','--check')
    print('Prepared',args.version,'protocol',version['protocol'],'Java',release,'NMS',sdk_dir.name,'API',api_version)


if __name__ == '__main__': main()
