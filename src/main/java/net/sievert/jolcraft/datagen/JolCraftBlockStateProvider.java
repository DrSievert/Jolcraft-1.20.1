package net.sievert.jolcraft.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.sievert.jolcraft.block.JolCraftBlocks;

public class JolCraftBlockStateProvider extends BlockStateProvider {
    public JolCraftBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JolCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(((RotatedPillarBlock) JolCraftBlocks.GEM_LOG.get()));
        axisBlock((RotatedPillarBlock) JolCraftBlocks.GEM_WOOD.get(), blockTexture(JolCraftBlocks.GEM_LOG.get()), blockTexture(JolCraftBlocks.GEM_LOG.get()));

        axisBlock((RotatedPillarBlock) JolCraftBlocks.STRIPPED_GEM_LOG.get(), new ResourceLocation(JolCraft.MOD_ID, "block/stripped_gem_log"),
                new ResourceLocation(JolCraft.MOD_ID, "block/stripped_gem_log_top"));
        axisBlock((RotatedPillarBlock) JolCraftBlocks.STRIPPED_GEM_WOOD.get(), new ResourceLocation(JolCraft.MOD_ID, "block/stripped_gem_log"),
                new ResourceLocation(JolCraft.MOD_ID, "block/stripped_gem_log"));

        blockWithItem(JolCraftBlocks.GEM_PLANKS);
        saplingBlock(JolCraftBlocks.GEM_SAPLING);

        simpleBlockItem(JolCraftBlocks.GEM_LOG.get(), models().withExistingParent("jolcraft:gem_log", "minecraft:block/cube_column"));
        simpleBlockItem(JolCraftBlocks.GEM_WOOD.get(), models().withExistingParent("jolcraft:gem_wood", "minecraft:block/cube_column"));
        simpleBlockItem(JolCraftBlocks.STRIPPED_GEM_LOG.get(), models().withExistingParent("jolcraft:stripped_gem_log", "minecraft:block/cube_column"));
        simpleBlockItem(JolCraftBlocks.STRIPPED_GEM_WOOD.get(), models().withExistingParent("jolcraft:stripped_gem_wood", "minecraft:block/cube_column"));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

}
