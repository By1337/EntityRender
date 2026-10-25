package dev.by1337.entityrender.core.metadata;

/** Generated from the NMS schema and reviewed default rules. Do not edit by hand. */
public final class GeneratedMetadata {
    private GeneratedMetadata() {
    }

    public static final class AgeableMob {
        private AgeableMob() {
        }

        // NMS: net.minecraft.world.entity.AgeableMob
        public static final MetadataKey<java.lang.Boolean> DATA_BABY_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.PathfinderMob.LAYOUT, DATA_BABY_ID);
    }

    public static final class AreaEffectCloud {
        private AreaEffectCloud() {
        }

        // NMS: net.minecraft.world.entity.AreaEffectCloud
        public static final MetadataKey<net.minecraft.core.particles.ParticleOptions> DATA_PARTICLE = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.PARTICLE);
        public static final MetadataKey<java.lang.Float> DATA_RADIUS = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<java.lang.Boolean> DATA_WAITING = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_PARTICLE, DATA_RADIUS, DATA_WAITING);
    }

    public static final class Avatar {
        private Avatar() {
        }

        // NMS: net.minecraft.world.entity.Avatar
        public static final MetadataKey<java.lang.Byte> DATA_PLAYER_MAIN_HAND = new MetadataKey<>(15, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataKey<java.lang.Byte> DATA_PLAYER_MODE_CUSTOMISATION = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.LivingEntity.LAYOUT, DATA_PLAYER_MAIN_HAND, DATA_PLAYER_MODE_CUSTOMISATION);
    }

    public static final class Display {
        private Display() {
        }

        // NMS: net.minecraft.world.entity.Display
        public static final MetadataKey<java.lang.Byte> DATA_BILLBOARD_RENDER_CONSTRAINTS_ID = new MetadataKey<>(15, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataKey<java.lang.Integer> DATA_BRIGHTNESS_OVERRIDE_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<java.lang.Integer> DATA_GLOW_COLOR_OVERRIDE_ID = new MetadataKey<>(22, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<java.lang.Float> DATA_HEIGHT_ID = new MetadataKey<>(21, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<org.joml.Quaternionf> DATA_LEFT_ROTATION_ID = new MetadataKey<>(13, net.minecraft.network.syncher.EntityDataSerializers.QUATERNION, value -> new org.joml.Quaternionf(value));
        public static final MetadataKey<java.lang.Integer> DATA_POS_ROT_INTERPOLATION_DURATION_ID = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<org.joml.Quaternionf> DATA_RIGHT_ROTATION_ID = new MetadataKey<>(14, net.minecraft.network.syncher.EntityDataSerializers.QUATERNION, value -> new org.joml.Quaternionf(value));
        public static final MetadataKey<org.joml.Vector3f> DATA_SCALE_ID = new MetadataKey<>(12, net.minecraft.network.syncher.EntityDataSerializers.VECTOR3, value -> new org.joml.Vector3f(value));
        public static final MetadataKey<java.lang.Float> DATA_SHADOW_RADIUS_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<java.lang.Float> DATA_SHADOW_STRENGTH_ID = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<java.lang.Integer> DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<java.lang.Integer> DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<org.joml.Vector3f> DATA_TRANSLATION_ID = new MetadataKey<>(11, net.minecraft.network.syncher.EntityDataSerializers.VECTOR3, value -> new org.joml.Vector3f(value));
        public static final MetadataKey<java.lang.Float> DATA_VIEW_RANGE_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<java.lang.Float> DATA_WIDTH_ID = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, DATA_BRIGHTNESS_OVERRIDE_ID, DATA_GLOW_COLOR_OVERRIDE_ID, DATA_HEIGHT_ID, DATA_LEFT_ROTATION_ID, DATA_POS_ROT_INTERPOLATION_DURATION_ID, DATA_RIGHT_ROTATION_ID, DATA_SCALE_ID, DATA_SHADOW_RADIUS_ID, DATA_SHADOW_STRENGTH_ID, DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID, DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID, DATA_TRANSLATION_ID, DATA_VIEW_RANGE_ID, DATA_WIDTH_ID)
                .withDefault(DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, (byte) 0)
                .withDefault(DATA_BRIGHTNESS_OVERRIDE_ID, -1)
                .withDefault(DATA_GLOW_COLOR_OVERRIDE_ID, -1)
                .withDefault(DATA_HEIGHT_ID, 0f)
                .withDefault(DATA_LEFT_ROTATION_ID, new org.joml.Quaternionf())
                .withDefault(DATA_POS_ROT_INTERPOLATION_DURATION_ID, 0)
                .withDefault(DATA_RIGHT_ROTATION_ID, new org.joml.Quaternionf())
                .withDefault(DATA_SCALE_ID, new org.joml.Vector3f(1))
                .withDefault(DATA_SHADOW_RADIUS_ID, 0f)
                .withDefault(DATA_SHADOW_STRENGTH_ID, 1f)
                .withDefault(DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID, 0)
                .withDefault(DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID, 0)
                .withDefault(DATA_TRANSLATION_ID, new org.joml.Vector3f())
                .withDefault(DATA_VIEW_RANGE_ID, 1f)
                .withDefault(DATA_WIDTH_ID, 0f);

        public static final class BlockDisplay {
            private BlockDisplay() {
            }

            // NMS: net.minecraft.world.entity.Display$BlockDisplay
            public static final MetadataKey<net.minecraft.world.level.block.state.BlockState> DATA_BLOCK_STATE_ID = new MetadataKey<>(23, net.minecraft.network.syncher.EntityDataSerializers.BLOCK_STATE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Display.LAYOUT, DATA_BLOCK_STATE_ID)
                    .withDefault(DATA_BLOCK_STATE_ID, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState());
        }

        public static final class ItemDisplay {
            private ItemDisplay() {
            }

            // NMS: net.minecraft.world.entity.Display$ItemDisplay
            public static final MetadataKey<java.lang.Byte> DATA_ITEM_DISPLAY_ID = new MetadataKey<>(24, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM_STACK_ID = new MetadataKey<>(23, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Display.LAYOUT, DATA_ITEM_DISPLAY_ID, DATA_ITEM_STACK_ID)
                    .withDefault(DATA_ITEM_DISPLAY_ID, (byte) 0)
                    .withDefault(DATA_ITEM_STACK_ID, net.minecraft.world.item.ItemStack.EMPTY);
        }

        public static final class TextDisplay {
            private TextDisplay() {
            }

            // NMS: net.minecraft.world.entity.Display$TextDisplay
            public static final MetadataKey<java.lang.Integer> DATA_BACKGROUND_COLOR_ID = new MetadataKey<>(25, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Integer> DATA_LINE_WIDTH_ID = new MetadataKey<>(24, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Byte> DATA_STYLE_FLAGS_ID = new MetadataKey<>(27, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<net.minecraft.network.chat.Component> DATA_TEXT_ID = new MetadataKey<>(23, net.minecraft.network.syncher.EntityDataSerializers.COMPONENT);
            public static final MetadataKey<java.lang.Byte> DATA_TEXT_OPACITY_ID = new MetadataKey<>(26, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Display.LAYOUT, DATA_BACKGROUND_COLOR_ID, DATA_LINE_WIDTH_ID, DATA_STYLE_FLAGS_ID, DATA_TEXT_ID, DATA_TEXT_OPACITY_ID)
                    .withDefault(DATA_BACKGROUND_COLOR_ID, 0x40000000)
                    .withDefault(DATA_LINE_WIDTH_ID, 200)
                    .withDefault(DATA_STYLE_FLAGS_ID, (byte) 0)
                    .withDefault(DATA_TEXT_ID, net.minecraft.network.chat.Component.empty())
                    .withDefault(DATA_TEXT_OPACITY_ID, (byte) -1);
        }
    }

    public static final class Entity {
        private Entity() {
        }

        // NMS: net.minecraft.world.entity.Entity
        public static final MetadataKey<java.lang.Integer> DATA_AIR_SUPPLY_ID = new MetadataKey<>(1, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<java.util.Optional<net.minecraft.network.chat.Component>> DATA_CUSTOM_NAME = new MetadataKey<>(2, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_COMPONENT);
        public static final MetadataKey<java.lang.Boolean> DATA_CUSTOM_NAME_VISIBLE = new MetadataKey<>(3, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataKey<java.lang.Boolean> DATA_NO_GRAVITY = new MetadataKey<>(5, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataKey<net.minecraft.world.entity.Pose> DATA_POSE = new MetadataKey<>(6, net.minecraft.network.syncher.EntityDataSerializers.POSE);
        public static final MetadataKey<java.lang.Byte> DATA_SHARED_FLAGS_ID = new MetadataKey<>(0, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataKey<java.lang.Boolean> DATA_SILENT = new MetadataKey<>(4, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataKey<java.lang.Integer> DATA_TICKS_FROZEN = new MetadataKey<>(7, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataLayout LAYOUT = new MetadataLayout(null, DATA_AIR_SUPPLY_ID, DATA_CUSTOM_NAME, DATA_CUSTOM_NAME_VISIBLE, DATA_NO_GRAVITY, DATA_POSE, DATA_SHARED_FLAGS_ID, DATA_SILENT, DATA_TICKS_FROZEN)
                .withDefault(DATA_AIR_SUPPLY_ID, 300)
                .withDefault(DATA_CUSTOM_NAME, java.util.Optional.empty())
                .withDefault(DATA_CUSTOM_NAME_VISIBLE, false)
                .withDefault(DATA_NO_GRAVITY, false)
                .withDefault(DATA_POSE, net.minecraft.world.entity.Pose.STANDING)
                .withDefault(DATA_SHARED_FLAGS_ID, (byte) 0)
                .withDefault(DATA_SILENT, false)
                .withDefault(DATA_TICKS_FROZEN, 0);
    }

    public static final class ExperienceOrb {
        private ExperienceOrb() {
        }

        // NMS: net.minecraft.world.entity.ExperienceOrb
        public static final MetadataKey<java.lang.Integer> DATA_VALUE = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_VALUE);
    }

    public static final class GlowSquid {
        private GlowSquid() {
        }

        // NMS: net.minecraft.world.entity.GlowSquid
        public static final MetadataKey<java.lang.Integer> DATA_DARK_TICKS_REMAINING = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Squid.LAYOUT, DATA_DARK_TICKS_REMAINING);
    }

    public static final class Interaction {
        private Interaction() {
        }

        // NMS: net.minecraft.world.entity.Interaction
        public static final MetadataKey<java.lang.Float> DATA_HEIGHT_ID = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<java.lang.Boolean> DATA_RESPONSE_ID = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataKey<java.lang.Float> DATA_WIDTH_ID = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_HEIGHT_ID, DATA_RESPONSE_ID, DATA_WIDTH_ID);
    }

    public static final class LightningBolt {
        private LightningBolt() {
        }

        // NMS: net.minecraft.world.entity.LightningBolt
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT);
    }

    public static final class LivingEntity {
        private LivingEntity() {
        }

        // NMS: net.minecraft.world.entity.LivingEntity
        public static final MetadataKey<java.lang.Integer> DATA_ARROW_COUNT_ID = new MetadataKey<>(12, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<java.lang.Boolean> DATA_EFFECT_AMBIENCE_ID = new MetadataKey<>(11, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
        public static final MetadataKey<java.util.List<net.minecraft.core.particles.ParticleOptions>> DATA_EFFECT_PARTICLES = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.PARTICLES);
        public static final MetadataKey<java.lang.Float> DATA_HEALTH_ID = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
        public static final MetadataKey<java.lang.Byte> DATA_LIVING_ENTITY_FLAGS = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataKey<java.lang.Integer> DATA_STINGER_COUNT_ID = new MetadataKey<>(13, net.minecraft.network.syncher.EntityDataSerializers.INT);
        public static final MetadataKey<java.util.Optional<net.minecraft.core.BlockPos>> SLEEPING_POS_ID = new MetadataKey<>(14, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_BLOCK_POS);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_ARROW_COUNT_ID, DATA_EFFECT_AMBIENCE_ID, DATA_EFFECT_PARTICLES, DATA_HEALTH_ID, DATA_LIVING_ENTITY_FLAGS, DATA_STINGER_COUNT_ID, SLEEPING_POS_ID)
                .withDefault(DATA_ARROW_COUNT_ID, 0)
                .withDefault(DATA_EFFECT_AMBIENCE_ID, false)
                .withDefault(DATA_EFFECT_PARTICLES, java.util.List.of())
                .withDefault(DATA_HEALTH_ID, 1f)
                .withDefault(DATA_LIVING_ENTITY_FLAGS, (byte) 0)
                .withDefault(DATA_STINGER_COUNT_ID, 0)
                .withDefault(SLEEPING_POS_ID, java.util.Optional.empty());
    }

    public static final class Marker {
        private Marker() {
        }

        // NMS: net.minecraft.world.entity.Marker
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT);
    }

    public static final class Mob {
        private Mob() {
        }

        // NMS: net.minecraft.world.entity.Mob
        public static final MetadataKey<java.lang.Byte> DATA_MOB_FLAGS_ID = new MetadataKey<>(15, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.LivingEntity.LAYOUT, DATA_MOB_FLAGS_ID);
    }

    public static final class OminousItemSpawner {
        private OminousItemSpawner() {
        }

        // NMS: net.minecraft.world.entity.OminousItemSpawner
        public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_ITEM);
    }

    public static final class PathfinderMob {
        private PathfinderMob() {
        }

        // NMS: net.minecraft.world.entity.PathfinderMob
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Mob.LAYOUT);
    }

    public static final class TamableAnimal {
        private TamableAnimal() {
        }

        // NMS: net.minecraft.world.entity.TamableAnimal
        public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
        public static final MetadataKey<java.util.Optional<net.minecraft.world.entity.EntityReference<net.minecraft.world.entity.LivingEntity>>> DATA_OWNERUUID_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
        public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_FLAGS_ID, DATA_OWNERUUID_ID);
    }

    public static final class ambient {
        private ambient() {
        }

        public static final class AmbientCreature {
            private AmbientCreature() {
            }

            // NMS: net.minecraft.world.entity.ambient.AmbientCreature
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Mob.LAYOUT);
        }

        public static final class Bat {
            private Bat() {
            }

            // NMS: net.minecraft.world.entity.ambient.Bat
            public static final MetadataKey<java.lang.Byte> DATA_ID_FLAGS = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.ambient.AmbientCreature.LAYOUT, DATA_ID_FLAGS);
        }
    }

    public static final class animal {
        private animal() {
        }

        public static final class AbstractCow {
            private AbstractCow() {
            }

            // NMS: net.minecraft.world.entity.animal.AbstractCow
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT);
        }

        public static final class AbstractFish {
            private AbstractFish() {
            }

            // NMS: net.minecraft.world.entity.animal.AbstractFish
            public static final MetadataKey<java.lang.Boolean> FROM_BUCKET = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.WaterAnimal.LAYOUT, FROM_BUCKET);
        }

        public static final class AbstractGolem {
            private AbstractGolem() {
            }

            // NMS: net.minecraft.world.entity.animal.AbstractGolem
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.PathfinderMob.LAYOUT);
        }

        public static final class AbstractSchoolingFish {
            private AbstractSchoolingFish() {
            }

            // NMS: net.minecraft.world.entity.animal.AbstractSchoolingFish
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractFish.LAYOUT);
        }

        public static final class AgeableWaterCreature {
            private AgeableWaterCreature() {
            }

            // NMS: net.minecraft.world.entity.animal.AgeableWaterCreature
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.AgeableMob.LAYOUT);
        }

        public static final class Animal {
            private Animal() {
            }

            // NMS: net.minecraft.world.entity.animal.Animal
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.AgeableMob.LAYOUT);
        }

        public static final class Bee {
            private Bee() {
            }

            // NMS: net.minecraft.world.entity.animal.Bee
            public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.lang.Integer> DATA_REMAINING_ANGER_TIME = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_FLAGS_ID, DATA_REMAINING_ANGER_TIME);
        }

        public static final class Cat {
            private Cat() {
            }

            // NMS: net.minecraft.world.entity.animal.Cat
            public static final MetadataKey<java.lang.Integer> DATA_COLLAR_COLOR = new MetadataKey<>(22, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.CatVariant>> DATA_VARIANT_ID = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.CAT_VARIANT);
            public static final MetadataKey<java.lang.Boolean> IS_LYING = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> RELAX_STATE_ONE = new MetadataKey<>(21, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.TamableAnimal.LAYOUT, DATA_COLLAR_COLOR, DATA_VARIANT_ID, IS_LYING, RELAX_STATE_ONE);
        }

        public static final class Chicken {
            private Chicken() {
            }

            // NMS: net.minecraft.world.entity.animal.Chicken
            public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.ChickenVariant>> DATA_VARIANT_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.CHICKEN_VARIANT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_VARIANT_ID);
        }

        public static final class Cod {
            private Cod() {
            }

            // NMS: net.minecraft.world.entity.animal.Cod
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractSchoolingFish.LAYOUT);
        }

        public static final class Cow {
            private Cow() {
            }

            // NMS: net.minecraft.world.entity.animal.Cow
            public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.CowVariant>> DATA_VARIANT_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.COW_VARIANT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractCow.LAYOUT, DATA_VARIANT_ID);
        }

        public static final class Dolphin {
            private Dolphin() {
            }

            // NMS: net.minecraft.world.entity.animal.Dolphin
            public static final MetadataKey<java.lang.Boolean> GOT_FISH = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Integer> MOISTNESS_LEVEL = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AgeableWaterCreature.LAYOUT, GOT_FISH, MOISTNESS_LEVEL);
        }

        public static final class Fox {
            private Fox() {
            }

            // NMS: net.minecraft.world.entity.animal.Fox
            public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.util.Optional<net.minecraft.world.entity.EntityReference<net.minecraft.world.entity.LivingEntity>>> DATA_TRUSTED_ID_0 = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
            public static final MetadataKey<java.util.Optional<net.minecraft.world.entity.EntityReference<net.minecraft.world.entity.LivingEntity>>> DATA_TRUSTED_ID_1 = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
            public static final MetadataKey<java.lang.Integer> DATA_TYPE_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_FLAGS_ID, DATA_TRUSTED_ID_0, DATA_TRUSTED_ID_1, DATA_TYPE_ID);
        }

        public static final class HappyGhast {
            private HappyGhast() {
            }

            // NMS: net.minecraft.world.entity.animal.HappyGhast
            public static final MetadataKey<java.lang.Boolean> IS_LEASH_HOLDER = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> STAYS_STILL = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, IS_LEASH_HOLDER, STAYS_STILL);
        }

        public static final class IronGolem {
            private IronGolem() {
            }

            // NMS: net.minecraft.world.entity.animal.IronGolem
            public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractGolem.LAYOUT, DATA_FLAGS_ID);
        }

        public static final class MushroomCow {
            private MushroomCow() {
            }

            // NMS: net.minecraft.world.entity.animal.MushroomCow
            public static final MetadataKey<java.lang.Integer> DATA_TYPE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractCow.LAYOUT, DATA_TYPE);
        }

        public static final class Ocelot {
            private Ocelot() {
            }

            // NMS: net.minecraft.world.entity.animal.Ocelot
            public static final MetadataKey<java.lang.Boolean> DATA_TRUSTING = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_TRUSTING);
        }

        public static final class Panda {
            private Panda() {
            }

            // NMS: net.minecraft.world.entity.animal.Panda
            public static final MetadataKey<java.lang.Byte> DATA_ID_FLAGS = new MetadataKey<>(22, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.lang.Integer> EAT_COUNTER = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Byte> HIDDEN_GENE_ID = new MetadataKey<>(21, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.lang.Byte> MAIN_GENE_ID = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.lang.Integer> SNEEZE_COUNTER = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Integer> UNHAPPY_COUNTER = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_ID_FLAGS, EAT_COUNTER, HIDDEN_GENE_ID, MAIN_GENE_ID, SNEEZE_COUNTER, UNHAPPY_COUNTER);
        }

        public static final class Parrot {
            private Parrot() {
            }

            // NMS: net.minecraft.world.entity.animal.Parrot
            public static final MetadataKey<java.lang.Integer> DATA_VARIANT_ID = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.ShoulderRidingEntity.LAYOUT, DATA_VARIANT_ID);
        }

        public static final class Pig {
            private Pig() {
            }

            // NMS: net.minecraft.world.entity.animal.Pig
            public static final MetadataKey<java.lang.Integer> DATA_BOOST_TIME = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.PigVariant>> DATA_VARIANT_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.PIG_VARIANT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_BOOST_TIME, DATA_VARIANT_ID);
        }

        public static final class PolarBear {
            private PolarBear() {
            }

            // NMS: net.minecraft.world.entity.animal.PolarBear
            public static final MetadataKey<java.lang.Boolean> DATA_STANDING_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_STANDING_ID);
        }

        public static final class Pufferfish {
            private Pufferfish() {
            }

            // NMS: net.minecraft.world.entity.animal.Pufferfish
            public static final MetadataKey<java.lang.Integer> PUFF_STATE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractFish.LAYOUT, PUFF_STATE);
        }

        public static final class Rabbit {
            private Rabbit() {
            }

            // NMS: net.minecraft.world.entity.animal.Rabbit
            public static final MetadataKey<java.lang.Integer> DATA_TYPE_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_TYPE_ID);
        }

        public static final class Salmon {
            private Salmon() {
            }

            // NMS: net.minecraft.world.entity.animal.Salmon
            public static final MetadataKey<java.lang.Integer> DATA_TYPE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractSchoolingFish.LAYOUT, DATA_TYPE);
        }

        public static final class ShoulderRidingEntity {
            private ShoulderRidingEntity() {
            }

            // NMS: net.minecraft.world.entity.animal.ShoulderRidingEntity
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.TamableAnimal.LAYOUT);
        }

        public static final class SnowGolem {
            private SnowGolem() {
            }

            // NMS: net.minecraft.world.entity.animal.SnowGolem
            public static final MetadataKey<java.lang.Byte> DATA_PUMPKIN_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractGolem.LAYOUT, DATA_PUMPKIN_ID);
        }

        public static final class Squid {
            private Squid() {
            }

            // NMS: net.minecraft.world.entity.animal.Squid
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AgeableWaterCreature.LAYOUT);
        }

        public static final class TropicalFish {
            private TropicalFish() {
            }

            // NMS: net.minecraft.world.entity.animal.TropicalFish
            public static final MetadataKey<java.lang.Integer> DATA_ID_TYPE_VARIANT = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractSchoolingFish.LAYOUT, DATA_ID_TYPE_VARIANT);
        }

        public static final class Turtle {
            private Turtle() {
            }

            // NMS: net.minecraft.world.entity.animal.Turtle
            public static final MetadataKey<java.lang.Boolean> HAS_EGG = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> LAYING_EGG = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, HAS_EGG, LAYING_EGG);
        }

        public static final class WaterAnimal {
            private WaterAnimal() {
            }

            // NMS: net.minecraft.world.entity.animal.WaterAnimal
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.PathfinderMob.LAYOUT);
        }

        public static final class allay {
            private allay() {
            }

            public static final class Allay {
                private Allay() {
                }

                // NMS: net.minecraft.world.entity.animal.allay.Allay
                public static final MetadataKey<java.lang.Boolean> DATA_CAN_DUPLICATE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Boolean> DATA_DANCING = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.PathfinderMob.LAYOUT, DATA_CAN_DUPLICATE, DATA_DANCING);
            }
        }

        public static final class armadillo {
            private armadillo() {
            }

            public static final class Armadillo {
                private Armadillo() {
                }

                // NMS: net.minecraft.world.entity.animal.armadillo.Armadillo
                public static final MetadataKey<net.minecraft.world.entity.animal.armadillo.Armadillo.ArmadilloState> ARMADILLO_STATE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.ARMADILLO_STATE);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, ARMADILLO_STATE);
            }
        }

        public static final class axolotl {
            private axolotl() {
            }

            public static final class Axolotl {
                private Axolotl() {
                }

                // NMS: net.minecraft.world.entity.animal.axolotl.Axolotl
                public static final MetadataKey<java.lang.Boolean> DATA_PLAYING_DEAD = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Integer> DATA_VARIANT = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<java.lang.Boolean> FROM_BUCKET = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_PLAYING_DEAD, DATA_VARIANT, FROM_BUCKET);
            }
        }

        public static final class camel {
            private camel() {
            }

            public static final class Camel {
                private Camel() {
                }

                // NMS: net.minecraft.world.entity.animal.camel.Camel
                public static final MetadataKey<java.lang.Boolean> DASH = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Long> LAST_POSE_CHANGE_TICK = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.LONG);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractHorse.LAYOUT, DASH, LAST_POSE_CHANGE_TICK);
            }
        }

        public static final class coppergolem {
            private coppergolem() {
            }

            public static final class CopperGolem {
                private CopperGolem() {
                }

                // NMS: net.minecraft.world.entity.animal.coppergolem.CopperGolem
                public static final MetadataKey<net.minecraft.world.entity.animal.coppergolem.CopperGolemState> COPPER_GOLEM_STATE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.COPPER_GOLEM_STATE);
                public static final MetadataKey<net.minecraft.world.level.block.WeatheringCopper.WeatherState> DATA_WEATHER_STATE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.WEATHERING_COPPER_STATE);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractGolem.LAYOUT, COPPER_GOLEM_STATE, DATA_WEATHER_STATE);
            }
        }

        public static final class frog {
            private frog() {
            }

            public static final class Frog {
                private Frog() {
                }

                // NMS: net.minecraft.world.entity.animal.frog.Frog
                public static final MetadataKey<java.util.OptionalInt> DATA_TONGUE_TARGET_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_UNSIGNED_INT);
                public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.frog.FrogVariant>> DATA_VARIANT_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.FROG_VARIANT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_TONGUE_TARGET_ID, DATA_VARIANT_ID);
            }

            public static final class Tadpole {
                private Tadpole() {
                }

                // NMS: net.minecraft.world.entity.animal.frog.Tadpole
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractFish.LAYOUT);
            }
        }

        public static final class goat {
            private goat() {
            }

            public static final class Goat {
                private Goat() {
                }

                // NMS: net.minecraft.world.entity.animal.goat.Goat
                public static final MetadataKey<java.lang.Boolean> DATA_HAS_LEFT_HORN = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Boolean> DATA_HAS_RIGHT_HORN = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Boolean> DATA_IS_SCREAMING_GOAT = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_HAS_LEFT_HORN, DATA_HAS_RIGHT_HORN, DATA_IS_SCREAMING_GOAT);
            }
        }

        public static final class horse {
            private horse() {
            }

            public static final class AbstractChestedHorse {
                private AbstractChestedHorse() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.AbstractChestedHorse
                public static final MetadataKey<java.lang.Boolean> DATA_ID_CHEST = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractHorse.LAYOUT, DATA_ID_CHEST);
            }

            public static final class AbstractHorse {
                private AbstractHorse() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.AbstractHorse
                public static final MetadataKey<java.lang.Byte> DATA_ID_FLAGS = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_ID_FLAGS);
            }

            public static final class Donkey {
                private Donkey() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.Donkey
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractChestedHorse.LAYOUT);
            }

            public static final class Horse {
                private Horse() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.Horse
                public static final MetadataKey<java.lang.Integer> DATA_ID_TYPE_VARIANT = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractHorse.LAYOUT, DATA_ID_TYPE_VARIANT);
            }

            public static final class Llama {
                private Llama() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.Llama
                public static final MetadataKey<java.lang.Integer> DATA_STRENGTH_ID = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<java.lang.Integer> DATA_VARIANT_ID = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractChestedHorse.LAYOUT, DATA_STRENGTH_ID, DATA_VARIANT_ID);
            }

            public static final class Mule {
                private Mule() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.Mule
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractChestedHorse.LAYOUT);
            }

            public static final class SkeletonHorse {
                private SkeletonHorse() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.SkeletonHorse
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractHorse.LAYOUT);
            }

            public static final class TraderLlama {
                private TraderLlama() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.TraderLlama
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.Llama.LAYOUT);
            }

            public static final class ZombieHorse {
                private ZombieHorse() {
                }

                // NMS: net.minecraft.world.entity.animal.horse.ZombieHorse
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.horse.AbstractHorse.LAYOUT);
            }
        }

        public static final class sheep {
            private sheep() {
            }

            public static final class Sheep {
                private Sheep() {
                }

                // NMS: net.minecraft.world.entity.animal.sheep.Sheep
                public static final MetadataKey<java.lang.Byte> DATA_WOOL_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_WOOL_ID);
            }
        }

        public static final class sniffer {
            private sniffer() {
            }

            public static final class Sniffer {
                private Sniffer() {
                }

                // NMS: net.minecraft.world.entity.animal.sniffer.Sniffer
                public static final MetadataKey<java.lang.Integer> DATA_DROP_SEED_AT_TICK = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<net.minecraft.world.entity.animal.sniffer.Sniffer.State> DATA_STATE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.SNIFFER_STATE);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_DROP_SEED_AT_TICK, DATA_STATE);
            }
        }

        public static final class wolf {
            private wolf() {
            }

            public static final class Wolf {
                private Wolf() {
                }

                // NMS: net.minecraft.world.entity.animal.wolf.Wolf
                public static final MetadataKey<java.lang.Integer> DATA_COLLAR_COLOR = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<java.lang.Boolean> DATA_INTERESTED_ID = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Integer> DATA_REMAINING_ANGER_TIME = new MetadataKey<>(21, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.wolf.WolfSoundVariant>> DATA_SOUND_VARIANT_ID = new MetadataKey<>(23, net.minecraft.network.syncher.EntityDataSerializers.WOLF_SOUND_VARIANT);
                public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.animal.wolf.WolfVariant>> DATA_VARIANT_ID = new MetadataKey<>(22, net.minecraft.network.syncher.EntityDataSerializers.WOLF_VARIANT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.TamableAnimal.LAYOUT, DATA_COLLAR_COLOR, DATA_INTERESTED_ID, DATA_REMAINING_ANGER_TIME, DATA_SOUND_VARIANT_ID, DATA_VARIANT_ID);
            }
        }
    }

    public static final class boss {
        private boss() {
        }

        public static final class enderdragon {
            private enderdragon() {
            }

            public static final class EndCrystal {
                private EndCrystal() {
                }

                // NMS: net.minecraft.world.entity.boss.enderdragon.EndCrystal
                public static final MetadataKey<java.util.Optional<net.minecraft.core.BlockPos>> DATA_BEAM_TARGET = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_BLOCK_POS);
                public static final MetadataKey<java.lang.Boolean> DATA_SHOW_BOTTOM = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_BEAM_TARGET, DATA_SHOW_BOTTOM);
            }

            public static final class EnderDragon {
                private EnderDragon() {
                }

                // NMS: net.minecraft.world.entity.boss.enderdragon.EnderDragon
                public static final MetadataKey<java.lang.Integer> DATA_PHASE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Mob.LAYOUT, DATA_PHASE);
            }
        }

        public static final class wither {
            private wither() {
            }

            public static final class WitherBoss {
                private WitherBoss() {
                }

                // NMS: net.minecraft.world.entity.boss.wither.WitherBoss
                public static final MetadataKey<java.lang.Integer> DATA_ID_INV = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<java.lang.Integer> DATA_TARGET_A = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<java.lang.Integer> DATA_TARGET_B = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataKey<java.lang.Integer> DATA_TARGET_C = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_ID_INV, DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C);
            }
        }
    }

    public static final class decoration {
        private decoration() {
        }

        public static final class ArmorStand {
            private ArmorStand() {
            }

            // NMS: net.minecraft.world.entity.decoration.ArmorStand
            public static final MetadataKey<net.minecraft.core.Rotations> DATA_BODY_POSE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.ROTATIONS);
            public static final MetadataKey<java.lang.Byte> DATA_CLIENT_FLAGS = new MetadataKey<>(15, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<net.minecraft.core.Rotations> DATA_HEAD_POSE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.ROTATIONS);
            public static final MetadataKey<net.minecraft.core.Rotations> DATA_LEFT_ARM_POSE = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.ROTATIONS);
            public static final MetadataKey<net.minecraft.core.Rotations> DATA_LEFT_LEG_POSE = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.ROTATIONS);
            public static final MetadataKey<net.minecraft.core.Rotations> DATA_RIGHT_ARM_POSE = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.ROTATIONS);
            public static final MetadataKey<net.minecraft.core.Rotations> DATA_RIGHT_LEG_POSE = new MetadataKey<>(21, net.minecraft.network.syncher.EntityDataSerializers.ROTATIONS);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.LivingEntity.LAYOUT, DATA_BODY_POSE, DATA_CLIENT_FLAGS, DATA_HEAD_POSE, DATA_LEFT_ARM_POSE, DATA_LEFT_LEG_POSE, DATA_RIGHT_ARM_POSE, DATA_RIGHT_LEG_POSE)
                    .withDefault(GeneratedMetadata.LivingEntity.DATA_HEALTH_ID, 20f)
                    .withDefault(DATA_BODY_POSE, net.minecraft.world.entity.decoration.ArmorStand.DEFAULT_BODY_POSE)
                    .withDefault(DATA_CLIENT_FLAGS, (byte) 0)
                    .withDefault(DATA_HEAD_POSE, net.minecraft.world.entity.decoration.ArmorStand.DEFAULT_HEAD_POSE)
                    .withDefault(DATA_LEFT_ARM_POSE, net.minecraft.world.entity.decoration.ArmorStand.DEFAULT_LEFT_ARM_POSE)
                    .withDefault(DATA_LEFT_LEG_POSE, net.minecraft.world.entity.decoration.ArmorStand.DEFAULT_LEFT_LEG_POSE)
                    .withDefault(DATA_RIGHT_ARM_POSE, net.minecraft.world.entity.decoration.ArmorStand.DEFAULT_RIGHT_ARM_POSE)
                    .withDefault(DATA_RIGHT_LEG_POSE, net.minecraft.world.entity.decoration.ArmorStand.DEFAULT_RIGHT_LEG_POSE);
        }

        public static final class BlockAttachedEntity {
            private BlockAttachedEntity() {
            }

            // NMS: net.minecraft.world.entity.decoration.BlockAttachedEntity
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT);
        }

        public static final class GlowItemFrame {
            private GlowItemFrame() {
            }

            // NMS: net.minecraft.world.entity.decoration.GlowItemFrame
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.decoration.ItemFrame.LAYOUT);
        }

        public static final class HangingEntity {
            private HangingEntity() {
            }

            // NMS: net.minecraft.world.entity.decoration.HangingEntity
            public static final MetadataKey<net.minecraft.core.Direction> DATA_DIRECTION = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.DIRECTION);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.decoration.BlockAttachedEntity.LAYOUT, DATA_DIRECTION);
        }

        public static final class ItemFrame {
            private ItemFrame() {
            }

            // NMS: net.minecraft.world.entity.decoration.ItemFrame
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataKey<java.lang.Integer> DATA_ROTATION = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.decoration.HangingEntity.LAYOUT, DATA_ITEM, DATA_ROTATION);
        }

        public static final class LeashFenceKnotEntity {
            private LeashFenceKnotEntity() {
            }

            // NMS: net.minecraft.world.entity.decoration.LeashFenceKnotEntity
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.decoration.BlockAttachedEntity.LAYOUT);
        }

        public static final class Mannequin {
            private Mannequin() {
            }

            // NMS: net.minecraft.world.entity.decoration.Mannequin
            public static final MetadataKey<java.util.Optional<net.minecraft.network.chat.Component>> DATA_DESCRIPTION = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_COMPONENT);
            public static final MetadataKey<java.lang.Boolean> DATA_IMMOVABLE = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<net.minecraft.world.item.component.ResolvableProfile> DATA_PROFILE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.RESOLVABLE_PROFILE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Avatar.LAYOUT, DATA_DESCRIPTION, DATA_IMMOVABLE, DATA_PROFILE);
        }

        public static final class Painting {
            private Painting() {
            }

            // NMS: net.minecraft.world.entity.decoration.Painting
            public static final MetadataKey<net.minecraft.core.Holder<net.minecraft.world.entity.decoration.PaintingVariant>> DATA_PAINTING_VARIANT_ID = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.PAINTING_VARIANT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.decoration.HangingEntity.LAYOUT, DATA_PAINTING_VARIANT_ID);
        }
    }

    public static final class item {
        private item() {
        }

        public static final class FallingBlockEntity {
            private FallingBlockEntity() {
            }

            // NMS: net.minecraft.world.entity.item.FallingBlockEntity
            public static final MetadataKey<net.minecraft.core.BlockPos> DATA_START_POS = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.BLOCK_POS);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_START_POS);
        }

        public static final class ItemEntity {
            private ItemEntity() {
            }

            // NMS: net.minecraft.world.entity.item.ItemEntity
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_ITEM);
        }

        public static final class PrimedTnt {
            private PrimedTnt() {
            }

            // NMS: net.minecraft.world.entity.item.PrimedTnt
            public static final MetadataKey<net.minecraft.world.level.block.state.BlockState> DATA_BLOCK_STATE_ID = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.BLOCK_STATE);
            public static final MetadataKey<java.lang.Integer> DATA_FUSE_ID = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_BLOCK_STATE_ID, DATA_FUSE_ID);
        }
    }

    public static final class monster {
        private monster() {
        }

        public static final class AbstractIllager {
            private AbstractIllager() {
            }

            // NMS: net.minecraft.world.entity.monster.AbstractIllager
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.raid.Raider.LAYOUT);
        }

        public static final class AbstractSkeleton {
            private AbstractSkeleton() {
            }

            // NMS: net.minecraft.world.entity.monster.AbstractSkeleton
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT);
        }

        public static final class Blaze {
            private Blaze() {
            }

            // NMS: net.minecraft.world.entity.monster.Blaze
            public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_FLAGS_ID);
        }

        public static final class Bogged {
            private Bogged() {
            }

            // NMS: net.minecraft.world.entity.monster.Bogged
            public static final MetadataKey<java.lang.Boolean> DATA_SHEARED = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractSkeleton.LAYOUT, DATA_SHEARED);
        }

        public static final class CaveSpider {
            private CaveSpider() {
            }

            // NMS: net.minecraft.world.entity.monster.CaveSpider
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Spider.LAYOUT);
        }

        public static final class Creeper {
            private Creeper() {
            }

            // NMS: net.minecraft.world.entity.monster.Creeper
            public static final MetadataKey<java.lang.Boolean> DATA_IS_IGNITED = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> DATA_IS_POWERED = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Integer> DATA_SWELL_DIR = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_IS_IGNITED, DATA_IS_POWERED, DATA_SWELL_DIR);
        }

        public static final class Drowned {
            private Drowned() {
            }

            // NMS: net.minecraft.world.entity.monster.Drowned
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Zombie.LAYOUT);
        }

        public static final class ElderGuardian {
            private ElderGuardian() {
            }

            // NMS: net.minecraft.world.entity.monster.ElderGuardian
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Guardian.LAYOUT);
        }

        public static final class EnderMan {
            private EnderMan() {
            }

            // NMS: net.minecraft.world.entity.monster.EnderMan
            public static final MetadataKey<java.util.Optional<net.minecraft.world.level.block.state.BlockState>> DATA_CARRY_STATE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_BLOCK_STATE);
            public static final MetadataKey<java.lang.Boolean> DATA_CREEPY = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> DATA_STARED_AT = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_CARRY_STATE, DATA_CREEPY, DATA_STARED_AT);
        }

        public static final class Endermite {
            private Endermite() {
            }

            // NMS: net.minecraft.world.entity.monster.Endermite
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT);
        }

        public static final class Evoker {
            private Evoker() {
            }

            // NMS: net.minecraft.world.entity.monster.Evoker
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.SpellcasterIllager.LAYOUT);
        }

        public static final class Ghast {
            private Ghast() {
            }

            // NMS: net.minecraft.world.entity.monster.Ghast
            public static final MetadataKey<java.lang.Boolean> DATA_IS_CHARGING = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Mob.LAYOUT, DATA_IS_CHARGING);
        }

        public static final class Giant {
            private Giant() {
            }

            // NMS: net.minecraft.world.entity.monster.Giant
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT);
        }

        public static final class Guardian {
            private Guardian() {
            }

            // NMS: net.minecraft.world.entity.monster.Guardian
            public static final MetadataKey<java.lang.Integer> DATA_ID_ATTACK_TARGET = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Boolean> DATA_ID_MOVING = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_ID_ATTACK_TARGET, DATA_ID_MOVING);
        }

        public static final class Husk {
            private Husk() {
            }

            // NMS: net.minecraft.world.entity.monster.Husk
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Zombie.LAYOUT);
        }

        public static final class Illusioner {
            private Illusioner() {
            }

            // NMS: net.minecraft.world.entity.monster.Illusioner
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.SpellcasterIllager.LAYOUT);
        }

        public static final class MagmaCube {
            private MagmaCube() {
            }

            // NMS: net.minecraft.world.entity.monster.MagmaCube
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Slime.LAYOUT);
        }

        public static final class Monster {
            private Monster() {
            }

            // NMS: net.minecraft.world.entity.monster.Monster
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.PathfinderMob.LAYOUT);
        }

        public static final class PatrollingMonster {
            private PatrollingMonster() {
            }

            // NMS: net.minecraft.world.entity.monster.PatrollingMonster
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT);
        }

        public static final class Phantom {
            private Phantom() {
            }

            // NMS: net.minecraft.world.entity.monster.Phantom
            public static final MetadataKey<java.lang.Integer> ID_SIZE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Mob.LAYOUT, ID_SIZE);
        }

        public static final class Pillager {
            private Pillager() {
            }

            // NMS: net.minecraft.world.entity.monster.Pillager
            public static final MetadataKey<java.lang.Boolean> IS_CHARGING_CROSSBOW = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractIllager.LAYOUT, IS_CHARGING_CROSSBOW);
        }

        public static final class Ravager {
            private Ravager() {
            }

            // NMS: net.minecraft.world.entity.monster.Ravager
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.raid.Raider.LAYOUT);
        }

        public static final class Shulker {
            private Shulker() {
            }

            // NMS: net.minecraft.world.entity.monster.Shulker
            public static final MetadataKey<net.minecraft.core.Direction> DATA_ATTACH_FACE_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.DIRECTION);
            public static final MetadataKey<java.lang.Byte> DATA_COLOR_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.lang.Byte> DATA_PEEK_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.AbstractGolem.LAYOUT, DATA_ATTACH_FACE_ID, DATA_COLOR_ID, DATA_PEEK_ID);
        }

        public static final class Silverfish {
            private Silverfish() {
            }

            // NMS: net.minecraft.world.entity.monster.Silverfish
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT);
        }

        public static final class Skeleton {
            private Skeleton() {
            }

            // NMS: net.minecraft.world.entity.monster.Skeleton
            public static final MetadataKey<java.lang.Boolean> DATA_STRAY_CONVERSION_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractSkeleton.LAYOUT, DATA_STRAY_CONVERSION_ID);
        }

        public static final class Slime {
            private Slime() {
            }

            // NMS: net.minecraft.world.entity.monster.Slime
            public static final MetadataKey<java.lang.Integer> ID_SIZE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Mob.LAYOUT, ID_SIZE);
        }

        public static final class SpellcasterIllager {
            private SpellcasterIllager() {
            }

            // NMS: net.minecraft.world.entity.monster.SpellcasterIllager
            public static final MetadataKey<java.lang.Byte> DATA_SPELL_CASTING_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractIllager.LAYOUT, DATA_SPELL_CASTING_ID);
        }

        public static final class Spider {
            private Spider() {
            }

            // NMS: net.minecraft.world.entity.monster.Spider
            public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_FLAGS_ID);
        }

        public static final class Stray {
            private Stray() {
            }

            // NMS: net.minecraft.world.entity.monster.Stray
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractSkeleton.LAYOUT);
        }

        public static final class Strider {
            private Strider() {
            }

            // NMS: net.minecraft.world.entity.monster.Strider
            public static final MetadataKey<java.lang.Integer> DATA_BOOST_TIME = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Boolean> DATA_SUFFOCATING = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_BOOST_TIME, DATA_SUFFOCATING);
        }

        public static final class Vex {
            private Vex() {
            }

            // NMS: net.minecraft.world.entity.monster.Vex
            public static final MetadataKey<java.lang.Byte> DATA_FLAGS_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_FLAGS_ID);
        }

        public static final class Vindicator {
            private Vindicator() {
            }

            // NMS: net.minecraft.world.entity.monster.Vindicator
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractIllager.LAYOUT);
        }

        public static final class Witch {
            private Witch() {
            }

            // NMS: net.minecraft.world.entity.monster.Witch
            public static final MetadataKey<java.lang.Boolean> DATA_USING_ITEM = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.raid.Raider.LAYOUT, DATA_USING_ITEM);
        }

        public static final class WitherSkeleton {
            private WitherSkeleton() {
            }

            // NMS: net.minecraft.world.entity.monster.WitherSkeleton
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.AbstractSkeleton.LAYOUT);
        }

        public static final class Zoglin {
            private Zoglin() {
            }

            // NMS: net.minecraft.world.entity.monster.Zoglin
            public static final MetadataKey<java.lang.Boolean> DATA_BABY_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_BABY_ID);
        }

        public static final class Zombie {
            private Zombie() {
            }

            // NMS: net.minecraft.world.entity.monster.Zombie
            public static final MetadataKey<java.lang.Boolean> DATA_BABY_ID = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> DATA_DROWNED_CONVERSION_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Integer> DATA_SPECIAL_TYPE_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_BABY_ID, DATA_DROWNED_CONVERSION_ID, DATA_SPECIAL_TYPE_ID);
        }

        public static final class ZombieVillager {
            private ZombieVillager() {
            }

            // NMS: net.minecraft.world.entity.monster.ZombieVillager
            public static final MetadataKey<java.lang.Boolean> DATA_CONVERTING_ID = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<net.minecraft.world.entity.npc.VillagerData> DATA_VILLAGER_DATA = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.VILLAGER_DATA);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Zombie.LAYOUT, DATA_CONVERTING_ID, DATA_VILLAGER_DATA);
        }

        public static final class ZombifiedPiglin {
            private ZombifiedPiglin() {
            }

            // NMS: net.minecraft.world.entity.monster.ZombifiedPiglin
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Zombie.LAYOUT);
        }

        public static final class breeze {
            private breeze() {
            }

            public static final class Breeze {
                private Breeze() {
                }

                // NMS: net.minecraft.world.entity.monster.breeze.Breeze
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT);
            }
        }

        public static final class creaking {
            private creaking() {
            }

            public static final class Creaking {
                private Creaking() {
                }

                // NMS: net.minecraft.world.entity.monster.creaking.Creaking
                public static final MetadataKey<java.lang.Boolean> CAN_MOVE = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.util.Optional<net.minecraft.core.BlockPos>> HOME_POS = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_BLOCK_POS);
                public static final MetadataKey<java.lang.Boolean> IS_ACTIVE = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Boolean> IS_TEARING_DOWN = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, CAN_MOVE, HOME_POS, IS_ACTIVE, IS_TEARING_DOWN);
            }
        }

        public static final class hoglin {
            private hoglin() {
            }

            public static final class Hoglin {
                private Hoglin() {
                }

                // NMS: net.minecraft.world.entity.monster.hoglin.Hoglin
                public static final MetadataKey<java.lang.Boolean> DATA_IMMUNE_TO_ZOMBIFICATION = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.animal.Animal.LAYOUT, DATA_IMMUNE_TO_ZOMBIFICATION);
            }
        }

        public static final class piglin {
            private piglin() {
            }

            public static final class AbstractPiglin {
                private AbstractPiglin() {
                }

                // NMS: net.minecraft.world.entity.monster.piglin.AbstractPiglin
                public static final MetadataKey<java.lang.Boolean> DATA_IMMUNE_TO_ZOMBIFICATION = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, DATA_IMMUNE_TO_ZOMBIFICATION);
            }

            public static final class Piglin {
                private Piglin() {
                }

                // NMS: net.minecraft.world.entity.monster.piglin.Piglin
                public static final MetadataKey<java.lang.Boolean> DATA_BABY_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Boolean> DATA_IS_CHARGING_CROSSBOW = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataKey<java.lang.Boolean> DATA_IS_DANCING = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.piglin.AbstractPiglin.LAYOUT, DATA_BABY_ID, DATA_IS_CHARGING_CROSSBOW, DATA_IS_DANCING);
            }

            public static final class PiglinBrute {
                private PiglinBrute() {
                }

                // NMS: net.minecraft.world.entity.monster.piglin.PiglinBrute
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.piglin.AbstractPiglin.LAYOUT);
            }
        }

        public static final class warden {
            private warden() {
            }

            public static final class Warden {
                private Warden() {
                }

                // NMS: net.minecraft.world.entity.monster.warden.Warden
                public static final MetadataKey<java.lang.Integer> CLIENT_ANGER_LEVEL = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.INT);
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.Monster.LAYOUT, CLIENT_ANGER_LEVEL);
            }
        }
    }

    public static final class npc {
        private npc() {
        }

        public static final class AbstractVillager {
            private AbstractVillager() {
            }

            // NMS: net.minecraft.world.entity.npc.AbstractVillager
            public static final MetadataKey<java.lang.Integer> DATA_UNHAPPY_COUNTER = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.AgeableMob.LAYOUT, DATA_UNHAPPY_COUNTER);
        }

        public static final class Villager {
            private Villager() {
            }

            // NMS: net.minecraft.world.entity.npc.Villager
            public static final MetadataKey<net.minecraft.world.entity.npc.VillagerData> DATA_VILLAGER_DATA = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.VILLAGER_DATA);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.npc.AbstractVillager.LAYOUT, DATA_VILLAGER_DATA);
        }

        public static final class WanderingTrader {
            private WanderingTrader() {
            }

            // NMS: net.minecraft.world.entity.npc.WanderingTrader
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.npc.AbstractVillager.LAYOUT);
        }
    }

    public static final class player {
        private player() {
        }

        public static final class Player {
            private Player() {
            }

            // NMS: net.minecraft.world.entity.player.Player
            public static final MetadataKey<java.lang.Float> DATA_PLAYER_ABSORPTION_ID = new MetadataKey<>(17, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
            public static final MetadataKey<java.lang.Integer> DATA_SCORE_ID = new MetadataKey<>(18, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.util.OptionalInt> DATA_SHOULDER_PARROT_LEFT = new MetadataKey<>(19, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_UNSIGNED_INT);
            public static final MetadataKey<java.util.OptionalInt> DATA_SHOULDER_PARROT_RIGHT = new MetadataKey<>(20, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_UNSIGNED_INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Avatar.LAYOUT, DATA_PLAYER_ABSORPTION_ID, DATA_SCORE_ID, DATA_SHOULDER_PARROT_LEFT, DATA_SHOULDER_PARROT_RIGHT);
        }
    }

    public static final class projectile {
        private projectile() {
        }

        public static final class AbstractArrow {
            private AbstractArrow() {
            }

            // NMS: net.minecraft.world.entity.projectile.AbstractArrow
            public static final MetadataKey<java.lang.Byte> ID_FLAGS = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataKey<java.lang.Boolean> IN_GROUND = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Byte> PIERCE_LEVEL = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT, ID_FLAGS, IN_GROUND, PIERCE_LEVEL);
        }

        public static final class AbstractHurtingProjectile {
            private AbstractHurtingProjectile() {
            }

            // NMS: net.minecraft.world.entity.projectile.AbstractHurtingProjectile
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT);
        }

        public static final class AbstractThrownPotion {
            private AbstractThrownPotion() {
            }

            // NMS: net.minecraft.world.entity.projectile.AbstractThrownPotion
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.ThrowableItemProjectile.LAYOUT);
        }

        public static final class Arrow {
            private Arrow() {
            }

            // NMS: net.minecraft.world.entity.projectile.Arrow
            public static final MetadataKey<java.lang.Integer> ID_EFFECT_COLOR = new MetadataKey<>(11, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractArrow.LAYOUT, ID_EFFECT_COLOR);
        }

        public static final class DragonFireball {
            private DragonFireball() {
            }

            // NMS: net.minecraft.world.entity.projectile.DragonFireball
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractHurtingProjectile.LAYOUT);
        }

        public static final class EvokerFangs {
            private EvokerFangs() {
            }

            // NMS: net.minecraft.world.entity.projectile.EvokerFangs
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT);
        }

        public static final class EyeOfEnder {
            private EyeOfEnder() {
            }

            // NMS: net.minecraft.world.entity.projectile.EyeOfEnder
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM_STACK = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_ITEM_STACK);
        }

        public static final class Fireball {
            private Fireball() {
            }

            // NMS: net.minecraft.world.entity.projectile.Fireball
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM_STACK = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractHurtingProjectile.LAYOUT, DATA_ITEM_STACK);
        }

        public static final class FireworkRocketEntity {
            private FireworkRocketEntity() {
            }

            // NMS: net.minecraft.world.entity.projectile.FireworkRocketEntity
            public static final MetadataKey<java.util.OptionalInt> DATA_ATTACHED_TO_TARGET = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_UNSIGNED_INT);
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ID_FIREWORKS_ITEM = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataKey<java.lang.Boolean> DATA_SHOT_AT_ANGLE = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT, DATA_ATTACHED_TO_TARGET, DATA_ID_FIREWORKS_ITEM, DATA_SHOT_AT_ANGLE);
        }

        public static final class FishingHook {
            private FishingHook() {
            }

            // NMS: net.minecraft.world.entity.projectile.FishingHook
            public static final MetadataKey<java.lang.Boolean> DATA_BITING = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Integer> DATA_HOOKED_ENTITY = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT, DATA_BITING, DATA_HOOKED_ENTITY);
        }

        public static final class LargeFireball {
            private LargeFireball() {
            }

            // NMS: net.minecraft.world.entity.projectile.LargeFireball
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Fireball.LAYOUT);
        }

        public static final class LlamaSpit {
            private LlamaSpit() {
            }

            // NMS: net.minecraft.world.entity.projectile.LlamaSpit
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT);
        }

        public static final class Projectile {
            private Projectile() {
            }

            // NMS: net.minecraft.world.entity.projectile.Projectile
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT);
        }

        public static final class ShulkerBullet {
            private ShulkerBullet() {
            }

            // NMS: net.minecraft.world.entity.projectile.ShulkerBullet
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT);
        }

        public static final class SmallFireball {
            private SmallFireball() {
            }

            // NMS: net.minecraft.world.entity.projectile.SmallFireball
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Fireball.LAYOUT);
        }

        public static final class Snowball {
            private Snowball() {
            }

            // NMS: net.minecraft.world.entity.projectile.Snowball
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.ThrowableItemProjectile.LAYOUT);
        }

        public static final class SpectralArrow {
            private SpectralArrow() {
            }

            // NMS: net.minecraft.world.entity.projectile.SpectralArrow
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractArrow.LAYOUT);
        }

        public static final class ThrowableItemProjectile {
            private ThrowableItemProjectile() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrowableItemProjectile
            public static final MetadataKey<net.minecraft.world.item.ItemStack> DATA_ITEM_STACK = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.ITEM_STACK);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.ThrowableProjectile.LAYOUT, DATA_ITEM_STACK);
        }

        public static final class ThrowableProjectile {
            private ThrowableProjectile() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrowableProjectile
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.Projectile.LAYOUT);
        }

        public static final class ThrownEgg {
            private ThrownEgg() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrownEgg
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.ThrowableItemProjectile.LAYOUT);
        }

        public static final class ThrownEnderpearl {
            private ThrownEnderpearl() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrownEnderpearl
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.ThrowableItemProjectile.LAYOUT);
        }

        public static final class ThrownExperienceBottle {
            private ThrownExperienceBottle() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrownExperienceBottle
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.ThrowableItemProjectile.LAYOUT);
        }

        public static final class ThrownLingeringPotion {
            private ThrownLingeringPotion() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrownLingeringPotion
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractThrownPotion.LAYOUT);
        }

        public static final class ThrownSplashPotion {
            private ThrownSplashPotion() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrownSplashPotion
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractThrownPotion.LAYOUT);
        }

        public static final class ThrownTrident {
            private ThrownTrident() {
            }

            // NMS: net.minecraft.world.entity.projectile.ThrownTrident
            public static final MetadataKey<java.lang.Boolean> ID_FOIL = new MetadataKey<>(12, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Byte> ID_LOYALTY = new MetadataKey<>(11, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractArrow.LAYOUT, ID_FOIL, ID_LOYALTY);
        }

        public static final class WitherSkull {
            private WitherSkull() {
            }

            // NMS: net.minecraft.world.entity.projectile.WitherSkull
            public static final MetadataKey<java.lang.Boolean> DATA_DANGEROUS = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractHurtingProjectile.LAYOUT, DATA_DANGEROUS);
        }

        public static final class windcharge {
            private windcharge() {
            }

            public static final class AbstractWindCharge {
                private AbstractWindCharge() {
                }

                // NMS: net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.AbstractHurtingProjectile.LAYOUT);
            }

            public static final class BreezeWindCharge {
                private BreezeWindCharge() {
                }

                // NMS: net.minecraft.world.entity.projectile.windcharge.BreezeWindCharge
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.windcharge.AbstractWindCharge.LAYOUT);
            }

            public static final class WindCharge {
                private WindCharge() {
                }

                // NMS: net.minecraft.world.entity.projectile.windcharge.WindCharge
                public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.projectile.windcharge.AbstractWindCharge.LAYOUT);
            }
        }
    }

    public static final class raid {
        private raid() {
        }

        public static final class Raider {
            private Raider() {
            }

            // NMS: net.minecraft.world.entity.raid.Raider
            public static final MetadataKey<java.lang.Boolean> IS_CELEBRATING = new MetadataKey<>(16, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.monster.PatrollingMonster.LAYOUT, IS_CELEBRATING);
        }
    }

    public static final class vehicle {
        private vehicle() {
        }

        public static final class AbstractBoat {
            private AbstractBoat() {
            }

            // NMS: net.minecraft.world.entity.vehicle.AbstractBoat
            public static final MetadataKey<java.lang.Integer> DATA_ID_BUBBLE_TIME = new MetadataKey<>(13, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Boolean> DATA_ID_PADDLE_LEFT = new MetadataKey<>(11, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataKey<java.lang.Boolean> DATA_ID_PADDLE_RIGHT = new MetadataKey<>(12, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.VehicleEntity.LAYOUT, DATA_ID_BUBBLE_TIME, DATA_ID_PADDLE_LEFT, DATA_ID_PADDLE_RIGHT);
        }

        public static final class AbstractChestBoat {
            private AbstractChestBoat() {
            }

            // NMS: net.minecraft.world.entity.vehicle.AbstractChestBoat
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractBoat.LAYOUT);
        }

        public static final class AbstractMinecart {
            private AbstractMinecart() {
            }

            // NMS: net.minecraft.world.entity.vehicle.AbstractMinecart
            public static final MetadataKey<java.util.Optional<net.minecraft.world.level.block.state.BlockState>> DATA_ID_CUSTOM_DISPLAY_BLOCK = new MetadataKey<>(11, net.minecraft.network.syncher.EntityDataSerializers.OPTIONAL_BLOCK_STATE);
            public static final MetadataKey<java.lang.Integer> DATA_ID_DISPLAY_OFFSET = new MetadataKey<>(12, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.VehicleEntity.LAYOUT, DATA_ID_CUSTOM_DISPLAY_BLOCK, DATA_ID_DISPLAY_OFFSET);
        }

        public static final class AbstractMinecartContainer {
            private AbstractMinecartContainer() {
            }

            // NMS: net.minecraft.world.entity.vehicle.AbstractMinecartContainer
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecart.LAYOUT);
        }

        public static final class Boat {
            private Boat() {
            }

            // NMS: net.minecraft.world.entity.vehicle.Boat
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractBoat.LAYOUT);
        }

        public static final class ChestBoat {
            private ChestBoat() {
            }

            // NMS: net.minecraft.world.entity.vehicle.ChestBoat
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractChestBoat.LAYOUT);
        }

        public static final class ChestRaft {
            private ChestRaft() {
            }

            // NMS: net.minecraft.world.entity.vehicle.ChestRaft
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractChestBoat.LAYOUT);
        }

        public static final class Minecart {
            private Minecart() {
            }

            // NMS: net.minecraft.world.entity.vehicle.Minecart
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecart.LAYOUT);
        }

        public static final class MinecartChest {
            private MinecartChest() {
            }

            // NMS: net.minecraft.world.entity.vehicle.MinecartChest
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecartContainer.LAYOUT);
        }

        public static final class MinecartCommandBlock {
            private MinecartCommandBlock() {
            }

            // NMS: net.minecraft.world.entity.vehicle.MinecartCommandBlock
            public static final MetadataKey<java.lang.String> DATA_ID_COMMAND_NAME = new MetadataKey<>(13, net.minecraft.network.syncher.EntityDataSerializers.STRING);
            public static final MetadataKey<net.minecraft.network.chat.Component> DATA_ID_LAST_OUTPUT = new MetadataKey<>(14, net.minecraft.network.syncher.EntityDataSerializers.COMPONENT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecart.LAYOUT, DATA_ID_COMMAND_NAME, DATA_ID_LAST_OUTPUT);
        }

        public static final class MinecartFurnace {
            private MinecartFurnace() {
            }

            // NMS: net.minecraft.world.entity.vehicle.MinecartFurnace
            public static final MetadataKey<java.lang.Boolean> DATA_ID_FUEL = new MetadataKey<>(13, net.minecraft.network.syncher.EntityDataSerializers.BOOLEAN);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecart.LAYOUT, DATA_ID_FUEL);
        }

        public static final class MinecartHopper {
            private MinecartHopper() {
            }

            // NMS: net.minecraft.world.entity.vehicle.MinecartHopper
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecartContainer.LAYOUT);
        }

        public static final class MinecartSpawner {
            private MinecartSpawner() {
            }

            // NMS: net.minecraft.world.entity.vehicle.MinecartSpawner
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecart.LAYOUT);
        }

        public static final class MinecartTNT {
            private MinecartTNT() {
            }

            // NMS: net.minecraft.world.entity.vehicle.MinecartTNT
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractMinecart.LAYOUT);
        }

        public static final class Raft {
            private Raft() {
            }

            // NMS: net.minecraft.world.entity.vehicle.Raft
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.vehicle.AbstractBoat.LAYOUT);
        }

        public static final class VehicleEntity {
            private VehicleEntity() {
            }

            // NMS: net.minecraft.world.entity.vehicle.VehicleEntity
            public static final MetadataKey<java.lang.Float> DATA_ID_DAMAGE = new MetadataKey<>(10, net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
            public static final MetadataKey<java.lang.Integer> DATA_ID_HURT = new MetadataKey<>(8, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataKey<java.lang.Integer> DATA_ID_HURTDIR = new MetadataKey<>(9, net.minecraft.network.syncher.EntityDataSerializers.INT);
            public static final MetadataLayout LAYOUT = new MetadataLayout(GeneratedMetadata.Entity.LAYOUT, DATA_ID_DAMAGE, DATA_ID_HURT, DATA_ID_HURTDIR);
        }
    }
}
