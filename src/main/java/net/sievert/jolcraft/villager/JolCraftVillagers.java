package net.sievert.jolcraft.villager;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.block.JolCraftBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.sound.JolCraftSounds;

import java.util.Set;

public class JolCraftVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, JolCraft.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, JolCraft.MOD_ID);


    //Banker
    public static final RegistryObject<PoiType> COIN_BLOCK_POI = POI_TYPES.register("coin_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(JolCraftBlocks.COIN_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> BANKER = VILLAGER_PROFESSIONS.register("banker",
            () -> new VillagerProfession("banker", x -> x.get() == COIN_BLOCK_POI.get(),
                    x -> x.get() == COIN_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CARTOGRAPHER));

    //Alchemist
    public static final RegistryObject<PoiType> ALCHEMIST_POI = POI_TYPES.register("alchemist_poi",
            () -> new PoiType(ImmutableSet.copyOf(JolCraftBlocks.ALCHEMIST_WORKBENCH.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> ALCHEMIST = VILLAGER_PROFESSIONS.register("alchemist",
            () -> new VillagerProfession("alchemist", x -> x.get() == ALCHEMIST_POI.get(),
                    x -> x.get() == ALCHEMIST_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    //Artisan
    public static final RegistryObject<PoiType> ARTISAN_POI = POI_TYPES.register("artisan_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.AMETHYST_BLOCK.getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> ARTISAN = VILLAGER_PROFESSIONS.register("artisan",
            () -> new VillagerProfession("artisan", x -> x.get() == ARTISAN_POI.get(),
                    x -> x.get() == ARTISAN_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_TOOLSMITH));

    //Blacksmith
    public static final RegistryObject<PoiType> BLACKSMITH_POI = POI_TYPES.register("blacksmith_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.IRON_BLOCK.getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> BLACKSMITH = VILLAGER_PROFESSIONS.register("blacksmith",
            () -> new VillagerProfession("blacksmith", x -> x.get() == BLACKSMITH_POI.get(),
                    x -> x.get() == BLACKSMITH_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));

    //Enchanter
    public static final RegistryObject<PoiType> ENCHANTER_POI = POI_TYPES.register("enchanter_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.ENCHANTING_TABLE.getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> ENCHANTER = VILLAGER_PROFESSIONS.register("enchanter",
            () -> new VillagerProfession("enchanter", x -> x.get() == ENCHANTER_POI.get(),
                    x -> x.get() == ENCHANTER_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_LIBRARIAN));

    //Smelter
    public static final RegistryObject<PoiType> SMELTER_POI = POI_TYPES.register("smelter_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.FURNACE.getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> SMELTER = VILLAGER_PROFESSIONS.register("smelter",
            () -> new VillagerProfession("smelter", x -> x.get() == SMELTER_POI.get(),
                    x -> x.get() == SMELTER_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_BUTCHER));

    //Warrior
    public static final RegistryObject<PoiType> WARRIOR_POI = POI_TYPES.register("warrior_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.TARGET.getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> WARRIOR = VILLAGER_PROFESSIONS.register("warrior",
            () -> new VillagerProfession("warrior", x -> x.get() == WARRIOR_POI.get(),
                    x -> x.get() == WARRIOR_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    JolCraftSounds.DWARF_YES.get()));

    //Miner

    private static final Set<BlockState> ORES = ImmutableList.of(Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.GOLD_ORE, Blocks.IRON_ORE).stream().flatMap((p_218093_) -> p_218093_.getStateDefinition().getPossibleStates().stream()).collect(ImmutableSet.toImmutableSet());

    public static final RegistryObject<PoiType> MINER_POI = POI_TYPES.register("miner_poi",
            () -> new PoiType(ImmutableSet.copyOf(ORES),
                    1, 1));

    public static final RegistryObject<VillagerProfession> MINER = VILLAGER_PROFESSIONS.register("miner",
            () -> new VillagerProfession("miner", x -> x.get() == MINER_POI.get(),
                    x -> x.get() == MINER_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_MASON));

    //King
    public static final RegistryObject<PoiType> KING_POI = POI_TYPES.register("king_poi",
            () -> new PoiType(ImmutableSet.copyOf(JolCraftBlocks.DWARVEN_GEM_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> KING = VILLAGER_PROFESSIONS.register("king",
            () -> new VillagerProfession("king", x -> x.get() == KING_POI.get(),
                    x -> x.get() == KING_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    JolCraftSounds.DWARF_YES.get()));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}