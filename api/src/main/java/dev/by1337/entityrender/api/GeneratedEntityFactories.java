package dev.by1337.entityrender.api;

public interface GeneratedEntityFactories {
    dev.by1337.entityrender.api.entity.VirtualEntity create(org.bukkit.entity.EntityType type, org.bukkit.Location location);

    default dev.by1337.entityrender.api.entity.VirtualAreaEffectCloud createAreaEffectCloud(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualAreaEffectCloud) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:area_effect_cloud"))), location);
    }

    default dev.by1337.entityrender.api.entity.VirtualExperienceOrb createExperienceOrb(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.VirtualExperienceOrb) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:experience_orb"))), location);
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

    default dev.by1337.entityrender.api.entity.animal.allay.VirtualAllay createAllay(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.allay.VirtualAllay) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:allay"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.armadillo.VirtualArmadillo createArmadillo(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.armadillo.VirtualArmadillo) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:armadillo"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.axolotl.VirtualAxolotl createAxolotl(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.axolotl.VirtualAxolotl) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:axolotl"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.bee.VirtualBee createBee(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.bee.VirtualBee) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bee"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.camel.VirtualCamel createCamel(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.camel.VirtualCamel) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:camel"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.camel.VirtualCamelHusk createCamelHusk(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.camel.VirtualCamelHusk) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:camel_husk"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.chicken.VirtualChicken createChicken(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.chicken.VirtualChicken) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:chicken"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.cow.VirtualCow createCow(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.cow.VirtualCow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cow"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.cow.VirtualMushroomCow createMooshroom(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.cow.VirtualMushroomCow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mooshroom"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.dolphin.VirtualDolphin createDolphin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.dolphin.VirtualDolphin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dolphin"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualDonkey createDonkey(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualDonkey) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:donkey"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualHorse createHorse(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualHorse) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:horse"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualLlama createLlama(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualLlama) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:llama"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualMule createMule(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualMule) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mule"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualSkeletonHorse createSkeletonHorse(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualSkeletonHorse) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:skeleton_horse"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualTraderLlama createTraderLlama(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualTraderLlama) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:trader_llama"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.equine.VirtualZombieHorse createZombieHorse(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.equine.VirtualZombieHorse) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie_horse"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.feline.VirtualCat createCat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.feline.VirtualCat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cat"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.feline.VirtualOcelot createOcelot(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.feline.VirtualOcelot) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ocelot"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.fish.VirtualCod createCod(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.fish.VirtualCod) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cod"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.fish.VirtualPufferfish createPufferfish(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.fish.VirtualPufferfish) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pufferfish"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.fish.VirtualSalmon createSalmon(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.fish.VirtualSalmon) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:salmon"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.fish.VirtualTropicalFish createTropicalFish(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.fish.VirtualTropicalFish) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:tropical_fish"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.fox.VirtualFox createFox(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.fox.VirtualFox) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:fox"))), location);
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

    default dev.by1337.entityrender.api.entity.animal.golem.VirtualCopperGolem createCopperGolem(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.golem.VirtualCopperGolem) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:copper_golem"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.golem.VirtualIronGolem createIronGolem(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.golem.VirtualIronGolem) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:iron_golem"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.golem.VirtualSnowGolem createSnowGolem(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.golem.VirtualSnowGolem) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:snow_golem"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.happyghast.VirtualHappyGhast createHappyGhast(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.happyghast.VirtualHappyGhast) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:happy_ghast"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.nautilus.VirtualNautilus createNautilus(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.nautilus.VirtualNautilus) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:nautilus"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.nautilus.VirtualZombieNautilus createZombieNautilus(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.nautilus.VirtualZombieNautilus) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie_nautilus"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.panda.VirtualPanda createPanda(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.panda.VirtualPanda) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:panda"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.parrot.VirtualParrot createParrot(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.parrot.VirtualParrot) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:parrot"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.pig.VirtualPig createPig(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.pig.VirtualPig) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pig"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.polarbear.VirtualPolarBear createPolarBear(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.polarbear.VirtualPolarBear) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:polar_bear"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.rabbit.VirtualRabbit createRabbit(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.rabbit.VirtualRabbit) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:rabbit"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.sheep.VirtualSheep createSheep(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.sheep.VirtualSheep) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:sheep"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.sniffer.VirtualSniffer createSniffer(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.sniffer.VirtualSniffer) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:sniffer"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.squid.VirtualGlowSquid createGlowSquid(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.squid.VirtualGlowSquid) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:glow_squid"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.squid.VirtualSquid createSquid(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.squid.VirtualSquid) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:squid"))), location);
    }

    default dev.by1337.entityrender.api.entity.animal.turtle.VirtualTurtle createTurtle(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.animal.turtle.VirtualTurtle) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:turtle"))), location);
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

    default dev.by1337.entityrender.api.entity.decoration.VirtualMannequin createMannequin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.decoration.VirtualMannequin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mannequin"))), location);
    }

    default dev.by1337.entityrender.api.entity.decoration.painting.VirtualPainting createPainting(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.decoration.painting.VirtualPainting) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:painting"))), location);
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

    default dev.by1337.entityrender.api.entity.monster.VirtualCreeper createCreeper(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualCreeper) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:creeper"))), location);
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

    default dev.by1337.entityrender.api.entity.monster.VirtualGhast createGhast(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualGhast) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ghast"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualGiant createGiant(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualGiant) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:giant"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualGuardian createGuardian(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualGuardian) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:guardian"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualMagmaCube createMagmaCube(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualMagmaCube) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:magma_cube"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualPhantom createPhantom(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualPhantom) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:phantom"))), location);
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

    default dev.by1337.entityrender.api.entity.monster.VirtualSlime createSlime(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualSlime) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:slime"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualStrider createStrider(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualStrider) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:strider"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualVex createVex(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualVex) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:vex"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualWitch createWitch(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualWitch) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:witch"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.VirtualZoglin createZoglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.VirtualZoglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zoglin"))), location);
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

    default dev.by1337.entityrender.api.entity.monster.illager.VirtualEvoker createEvoker(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.illager.VirtualEvoker) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:evoker"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.illager.VirtualIllusioner createIllusioner(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.illager.VirtualIllusioner) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:illusioner"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.illager.VirtualPillager createPillager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.illager.VirtualPillager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pillager"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.illager.VirtualVindicator createVindicator(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.illager.VirtualVindicator) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:vindicator"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglin createPiglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:piglin"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglinBrute createPiglinBrute(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglinBrute) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:piglin_brute"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.skeleton.VirtualBogged createBogged(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.skeleton.VirtualBogged) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bogged"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.skeleton.VirtualParched createParched(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.skeleton.VirtualParched) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:parched"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.skeleton.VirtualSkeleton createSkeleton(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.skeleton.VirtualSkeleton) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:skeleton"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.skeleton.VirtualStray createStray(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.skeleton.VirtualStray) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:stray"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.skeleton.VirtualWitherSkeleton createWitherSkeleton(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.skeleton.VirtualWitherSkeleton) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wither_skeleton"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.spider.VirtualCaveSpider createCaveSpider(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.spider.VirtualCaveSpider) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cave_spider"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.spider.VirtualSpider createSpider(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.spider.VirtualSpider) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spider"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.warden.VirtualWarden createWarden(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.warden.VirtualWarden) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:warden"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.zombie.VirtualDrowned createDrowned(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.zombie.VirtualDrowned) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:drowned"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.zombie.VirtualHusk createHusk(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.zombie.VirtualHusk) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:husk"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombie createZombie(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombie) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombieVillager createZombieVillager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombieVillager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombie_villager"))), location);
    }

    default dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombifiedPiglin createZombifiedPiglin(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombifiedPiglin) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:zombified_piglin"))), location);
    }

    default dev.by1337.entityrender.api.entity.npc.villager.VirtualVillager createVillager(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.npc.villager.VirtualVillager) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:villager"))), location);
    }

    default dev.by1337.entityrender.api.entity.npc.wanderingtrader.VirtualWanderingTrader createWanderingTrader(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.npc.wanderingtrader.VirtualWanderingTrader) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wandering_trader"))), location);
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

    default dev.by1337.entityrender.api.entity.projectile.VirtualLlamaSpit createLlamaSpit(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualLlamaSpit) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:llama_spit"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.VirtualShulkerBullet createShulkerBullet(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.VirtualShulkerBullet) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:shulker_bullet"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.arrow.VirtualArrow createArrow(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.arrow.VirtualArrow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:arrow"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.arrow.VirtualSpectralArrow createSpectralArrow(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.arrow.VirtualSpectralArrow) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spectral_arrow"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.arrow.VirtualThrownTrident createTrident(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.arrow.VirtualThrownTrident) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:trident"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualDragonFireball createDragonFireball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualDragonFireball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dragon_fireball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualLargeFireball createFireball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualLargeFireball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:fireball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualSmallFireball createSmallFireball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualSmallFireball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:small_fireball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualWitherSkull createWitherSkull(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.VirtualWitherSkull) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wither_skull"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.windcharge.VirtualBreezeWindCharge createBreezeWindCharge(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.windcharge.VirtualBreezeWindCharge) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:breeze_wind_charge"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.windcharge.VirtualWindCharge createWindCharge(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.hurtingprojectile.windcharge.VirtualWindCharge) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:wind_charge"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualSnowball createSnowball(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualSnowball) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:snowball"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownEgg createEgg(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownEgg) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:egg"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownEnderpearl createEnderPearl(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownEnderpearl) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:ender_pearl"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownExperienceBottle createExperienceBottle(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownExperienceBottle) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:experience_bottle"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownLingeringPotion createLingeringPotion(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownLingeringPotion) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:lingering_potion"))), location);
    }

    default dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownSplashPotion createSplashPotion(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrownSplashPotion) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:splash_potion"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createAcaciaBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:acacia_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createBirchBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:birch_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createCherryBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cherry_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createDarkOakBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dark_oak_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createJungleBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:jungle_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createMangroveBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mangrove_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createOakBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:oak_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createPaleOakBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pale_oak_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat createSpruceBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spruce_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createAcaciaChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:acacia_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createBirchChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:birch_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createCherryChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:cherry_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createDarkOakChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:dark_oak_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createJungleChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:jungle_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createMangroveChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:mangrove_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createOakChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:oak_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createPaleOakChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:pale_oak_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat createSpruceChestBoat(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestBoat) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spruce_chest_boat"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestRaft createBambooChestRaft(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualChestRaft) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bamboo_chest_raft"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.boat.VirtualRaft createBambooRaft(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.boat.VirtualRaft) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:bamboo_raft"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecart createMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecart) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartChest createChestMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartChest) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:chest_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartCommandBlock createCommandBlockMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartCommandBlock) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:command_block_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartFurnace createFurnaceMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartFurnace) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:furnace_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartHopper createHopperMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartHopper) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:hopper_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartSpawner createSpawnerMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartSpawner) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:spawner_minecart"))), location);
    }

    default dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartTNT createTntMinecart(org.bukkit.Location location) {
        return (dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartTNT) create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString("minecraft:tnt_minecart"))), location);
    }
}
