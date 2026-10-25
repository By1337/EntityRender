package dev.by1337.entityrender.api;

public interface GeneratedEntityFactories {
    dev.by1337.entityrender.api.entity.VirtualEntity create(org.bukkit.entity.EntityType type, org.bukkit.Location location);

    default dev.by1337.entityrender.api.entity.VirtualAreaEffectCloud createAreaEffectCloud(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualAreaEffectCloud) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:area_effect_cloud"))), location);
    }

    default dev.by1337.entityrender.api.entity.VirtualExperienceOrb createExperienceOrb(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualExperienceOrb) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:experience_orb"))), location);
    }

    default dev.by1337.entityrender.api.entity.VirtualGlowSquid createGlowSquid(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualGlowSquid) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:glow_squid"))), location);
    }

    default dev.by1337.entityrender.api.entity.VirtualInteraction createInteraction(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualInteraction) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:interaction"))), location);
    }

    default dev.by1337.entityrender.api.entity.VirtualLightningBolt createLightningBolt(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualLightningBolt) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:lightning_bolt"))), location);
    }

    default dev.by1337.entityrender.api.entity.VirtualOminousItemSpawner createOminousItemSpawner(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualOminousItemSpawner) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ominous_item_spawner"))), location);
    }

    default dev.by1337.entityrender.api.entity.ambient.VirtualBat createBat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.ambient.VirtualBat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bat"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualBee createBee(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualBee) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bee"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualCat createCat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualCat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cat"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualChicken createChicken(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualChicken) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:chicken"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualCod createCod(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualCod) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cod"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualCow createCow(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualCow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cow"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualDolphin createDolphin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualDolphin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dolphin"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualFox createFox(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualFox) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:fox"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualIronGolem createIronGolem(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualIronGolem) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:iron_golem"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualMushroomCow createMooshroom(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualMushroomCow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mooshroom"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualOcelot createOcelot(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualOcelot) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ocelot"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualPanda createPanda(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualPanda) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:panda"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualParrot createParrot(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualParrot) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:parrot"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualPig createPig(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualPig) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pig"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualPolarBear createPolarBear(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualPolarBear) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:polar_bear"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualPufferfish createPufferfish(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualPufferfish) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pufferfish"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualRabbit createRabbit(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualRabbit) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:rabbit"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualSalmon createSalmon(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualSalmon) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:salmon"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualSnowGolem createSnowGolem(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualSnowGolem) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:snow_golem"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualSquid createSquid(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualSquid) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:squid"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualTropicalFish createTropicalFish(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualTropicalFish) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:tropical_fish"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.VirtualTurtle createTurtle(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.VirtualTurtle) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:turtle"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.allay.VirtualAllay createAllay(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.allay.VirtualAllay) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:allay"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.armadillo.VirtualArmadillo createArmadillo(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.armadillo.VirtualArmadillo) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:armadillo"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.axolotl.VirtualAxolotl createAxolotl(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.axolotl.VirtualAxolotl) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:axolotl"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.camel.VirtualCamel createCamel(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.camel.VirtualCamel) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:camel"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.frog.VirtualFrog createFrog(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.frog.VirtualFrog) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:frog"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.frog.VirtualTadpole createTadpole(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.frog.VirtualTadpole) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:tadpole"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.goat.VirtualGoat createGoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.goat.VirtualGoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:goat"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualDonkey createDonkey(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualDonkey) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:donkey"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualHorse createHorse(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualHorse) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:horse"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualLlama createLlama(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualLlama) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:llama"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualMule createMule(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualMule) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mule"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualSkeletonHorse createSkeletonHorse(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualSkeletonHorse) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:skeleton_horse"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualTraderLlama createTraderLlama(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualTraderLlama) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:trader_llama"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.horse.VirtualZombieHorse createZombieHorse(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.horse.VirtualZombieHorse) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie_horse"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.sheep.VirtualSheep createSheep(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.sheep.VirtualSheep) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:sheep"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.sniffer.VirtualSniffer createSniffer(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.sniffer.VirtualSniffer) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:sniffer"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.wolf.VirtualWolf createWolf(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.wolf.VirtualWolf) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wolf"))), location);
    }

    default dev.by1337.entityrender.api.entity.boss.enderdragon.VirtualEndCrystal createEndCrystal(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.boss.enderdragon.VirtualEndCrystal) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:end_crystal"))), location);
    }

    default dev.by1337.entityrender.api.entity.boss.enderdragon.VirtualEnderDragon createEnderDragon(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.boss.enderdragon.VirtualEnderDragon) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ender_dragon"))), location);
    }

    default dev.by1337.entityrender.api.entity.boss.wither.VirtualWitherBoss createWither(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.boss.wither.VirtualWitherBoss) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wither"))), location);
    }

    default dev.by1337.entityrender.api.entity.decoration.VirtualGlowItemFrame createGlowItemFrame(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.decoration.VirtualGlowItemFrame) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:glow_item_frame"))), location);
    }

    default dev.by1337.entityrender.api.entity.decoration.VirtualItemFrame createItemFrame(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.decoration.VirtualItemFrame) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:item_frame"))), location);
    }

    default dev.by1337.entityrender.api.entity.decoration.VirtualLeashFenceKnotEntity createLeashKnot(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.decoration.VirtualLeashFenceKnotEntity) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:leash_knot"))), location);
    }

    default dev.by1337.entityrender.api.entity.decoration.VirtualPainting createPainting(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.decoration.VirtualPainting) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:painting"))), location);
    }

    default dev.by1337.entityrender.api.entity.item.VirtualFallingBlockEntity createFallingBlock(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.item.VirtualFallingBlockEntity) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:falling_block"))), location);
    }

    default dev.by1337.entityrender.api.entity.item.VirtualItemEntity createItem(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.item.VirtualItemEntity) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:item"))), location);
    }

    default dev.by1337.entityrender.api.entity.item.VirtualPrimedTnt createTnt(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.item.VirtualPrimedTnt) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:tnt"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualBlaze createBlaze(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualBlaze) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:blaze"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualBogged createBogged(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualBogged) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bogged"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualCaveSpider createCaveSpider(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualCaveSpider) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cave_spider"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualCreeper createCreeper(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualCreeper) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:creeper"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualDrowned createDrowned(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualDrowned) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:drowned"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualElderGuardian createElderGuardian(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualElderGuardian) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:elder_guardian"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualEnderMan createEnderman(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualEnderMan) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:enderman"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualEndermite createEndermite(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualEndermite) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:endermite"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualEvoker createEvoker(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualEvoker) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:evoker"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualGhast createGhast(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualGhast) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ghast"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualGiant createGiant(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualGiant) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:giant"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualGuardian createGuardian(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualGuardian) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:guardian"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualHusk createHusk(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualHusk) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:husk"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualIllusioner createIllusioner(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualIllusioner) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:illusioner"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualMagmaCube createMagmaCube(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualMagmaCube) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:magma_cube"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualPhantom createPhantom(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualPhantom) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:phantom"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualPillager createPillager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualPillager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pillager"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualRavager createRavager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualRavager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ravager"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualShulker createShulker(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualShulker) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:shulker"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualSilverfish createSilverfish(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualSilverfish) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:silverfish"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualSkeleton createSkeleton(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualSkeleton) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:skeleton"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualSlime createSlime(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualSlime) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:slime"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualSpider createSpider(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualSpider) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spider"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualStray createStray(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualStray) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:stray"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualStrider createStrider(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualStrider) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:strider"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualVex createVex(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualVex) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:vex"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualVindicator createVindicator(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualVindicator) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:vindicator"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualWitch createWitch(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualWitch) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:witch"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualWitherSkeleton createWitherSkeleton(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualWitherSkeleton) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wither_skeleton"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualZoglin createZoglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualZoglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zoglin"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualZombie createZombie(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualZombie) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualZombieVillager createZombieVillager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualZombieVillager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie_villager"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualZombifiedPiglin createZombifiedPiglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualZombifiedPiglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombified_piglin"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.breeze.VirtualBreeze createBreeze(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.breeze.VirtualBreeze) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:breeze"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.creaking.VirtualCreaking createCreaking(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.creaking.VirtualCreaking) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:creaking"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.hoglin.VirtualHoglin createHoglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.hoglin.VirtualHoglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:hoglin"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglin createPiglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:piglin"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglinBrute createPiglinBrute(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglinBrute) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:piglin_brute"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.warden.VirtualWarden createWarden(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.warden.VirtualWarden) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:warden"))), location);
    }

    default dev.by1337.entityrender.api.entity.npc.VirtualVillager createVillager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.npc.VirtualVillager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:villager"))), location);
    }

    default dev.by1337.entityrender.api.entity.npc.VirtualWanderingTrader createWanderingTrader(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.npc.VirtualWanderingTrader) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wandering_trader"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualArrow createArrow(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualArrow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:arrow"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualDragonFireball createDragonFireball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualDragonFireball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dragon_fireball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualEvokerFangs createEvokerFangs(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualEvokerFangs) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:evoker_fangs"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualEyeOfEnder createEyeOfEnder(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualEyeOfEnder) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:eye_of_ender"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualFireworkRocketEntity createFireworkRocket(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualFireworkRocketEntity) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:firework_rocket"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualLargeFireball createFireball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualLargeFireball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:fireball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualLlamaSpit createLlamaSpit(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualLlamaSpit) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:llama_spit"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualShulkerBullet createShulkerBullet(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualShulkerBullet) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:shulker_bullet"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualSmallFireball createSmallFireball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualSmallFireball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:small_fireball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualSnowball createSnowball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualSnowball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:snowball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualSpectralArrow createSpectralArrow(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualSpectralArrow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spectral_arrow"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualThrownEgg createEgg(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualThrownEgg) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:egg"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualThrownEnderpearl createEnderPearl(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualThrownEnderpearl) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ender_pearl"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualThrownExperienceBottle createExperienceBottle(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualThrownExperienceBottle) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:experience_bottle"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualThrownLingeringPotion createLingeringPotion(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualThrownLingeringPotion) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:lingering_potion"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualThrownSplashPotion createSplashPotion(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualThrownSplashPotion) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:splash_potion"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualThrownTrident createTrident(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualThrownTrident) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:trident"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualWitherSkull createWitherSkull(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualWitherSkull) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wither_skull"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.windcharge.VirtualBreezeWindCharge createBreezeWindCharge(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.windcharge.VirtualBreezeWindCharge) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:breeze_wind_charge"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.windcharge.VirtualWindCharge createWindCharge(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.windcharge.VirtualWindCharge) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wind_charge"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createAcaciaBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:acacia_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createBirchBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:birch_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createCherryBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cherry_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createDarkOakBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dark_oak_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createJungleBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:jungle_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createMangroveBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mangrove_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createOakBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:oak_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createPaleOakBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pale_oak_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualBoat createSpruceBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spruce_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createAcaciaChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:acacia_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createBirchChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:birch_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createCherryChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cherry_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createDarkOakChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dark_oak_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createJungleChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:jungle_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createMangroveChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mangrove_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createOakChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:oak_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createPaleOakChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pale_oak_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat createSpruceChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spruce_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualChestRaft createBambooChestRaft(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualChestRaft) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bamboo_chest_raft"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecart createMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecart) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartChest createChestMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartChest) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:chest_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartCommandBlock createCommandBlockMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartCommandBlock) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:command_block_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartFurnace createFurnaceMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartFurnace) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:furnace_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartHopper createHopperMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartHopper) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:hopper_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartSpawner createSpawnerMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartSpawner) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spawner_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartTNT createTntMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartTNT) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:tnt_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.VirtualRaft createBambooRaft(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.VirtualRaft) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bamboo_raft"))), location);
    }
}
