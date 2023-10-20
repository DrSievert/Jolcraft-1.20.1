package net.sievert.jolcraft.datagen;


import net.sievert.jolcraft.block.JolCraftBlocks;
import net.sievert.jolcraft.item.JolCraftItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class JolCraftBlockLootTables extends BlockLootSubProvider {
    public JolCraftBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(JolCraftBlocks.COIN_BLOCK.get());
        dropSelf(JolCraftBlocks.COIN_PRESS.get());
        dropSelf(JolCraftBlocks.ALCHEMIST_WORKBENCH.get());

        dropSelf(JolCraftBlocks.GEM_LOG.get());
        dropSelf(JolCraftBlocks.GEM_WOOD.get());
        dropSelf(JolCraftBlocks.GEM_LOG_CROSS.get());
        dropSelf(JolCraftBlocks.GEM_LOG_BRANCH.get());
        dropSelf(JolCraftBlocks.GEM_PLANKS.get());
        dropSelf(JolCraftBlocks.STRIPPED_GEM_LOG.get());
        dropSelf(JolCraftBlocks.STRIPPED_GEM_WOOD.get());
        dropSelf(JolCraftBlocks.GEM_SAPLING.get());

        //Ore
       add(JolCraftBlocks.DWARVEN_METAL_DEEPSLATE_ORE.get(),
                (block) -> createOreDrop(JolCraftBlocks.DWARVEN_METAL_DEEPSLATE_ORE.get(), JolCraftItems.UNREFINED_DWARVEN_METAL.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return JolCraftBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}