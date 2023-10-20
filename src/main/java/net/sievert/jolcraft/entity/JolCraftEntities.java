package net.sievert.jolcraft.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.*;
import net.sievert.jolcraft.entity.spirit.SpiritFoxEntity;

public class JolCraftEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JolCraft.MOD_ID);

    public static final RegistryObject<EntityType<SpiritFoxEntity>> SPIRIT_FOX =
            ENTITY_TYPES.register("spirit_fox",
                    () -> EntityType.Builder.of(SpiritFoxEntity::new, MobCategory.CREATURE)
                            .fireImmune()
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "spirit_fox").toString()));

    public static final RegistryObject<EntityType<DwarfEntity>> DWARF =
            ENTITY_TYPES.register("dwarf",
                    () -> EntityType.Builder.of(DwarfEntity::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf").toString()));

    public static final RegistryObject<EntityType<DwarfAlchemist>> DWARF_ALCHEMIST =
            ENTITY_TYPES.register("dwarf_alchemist",
                    () -> EntityType.Builder.of(DwarfAlchemist::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_alchemist").toString()));

    public static final RegistryObject<EntityType<DwarfArtisan>> DWARF_ARTISAN =
            ENTITY_TYPES.register("dwarf_artisan",
                    () -> EntityType.Builder.of(DwarfArtisan::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_artisan").toString()));

    public static final RegistryObject<EntityType<DwarfBlacksmith>> DWARF_BLACKSMITH =
            ENTITY_TYPES.register("dwarf_blacksmith",
                    () -> EntityType.Builder.of(DwarfBlacksmith::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_blacksmith").toString()));

    public static final RegistryObject<EntityType<DwarfEnchanter>> DWARF_ENCHANTER =
            ENTITY_TYPES.register("dwarf_enchanter",
                    () -> EntityType.Builder.of(DwarfEnchanter::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_enchanter").toString()));

    public static final RegistryObject<EntityType<DwarfSmelter>> DWARF_SMELTER =
            ENTITY_TYPES.register("dwarf_smelter",
                    () -> EntityType.Builder.of(DwarfSmelter::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_smelter").toString()));

    public static final RegistryObject<EntityType<DwarfWarrior>> DWARF_WARRIOR =
            ENTITY_TYPES.register("dwarf_warrior",
                    () -> EntityType.Builder.of(DwarfWarrior::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_warrior").toString()));

    public static final RegistryObject<EntityType<DwarfMiner>> DWARF_MINER =
            ENTITY_TYPES.register("dwarf_miner",
                    () -> EntityType.Builder.of(DwarfMiner::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_miner").toString()));

    public static final RegistryObject<EntityType<DwarfKing>> DWARF_KING =
            ENTITY_TYPES.register("dwarf_king",
                    () -> EntityType.Builder.of(DwarfKing::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(JolCraft.MOD_ID, "dwarf_king").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
