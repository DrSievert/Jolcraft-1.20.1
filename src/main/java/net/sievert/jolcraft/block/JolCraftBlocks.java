package net.sievert.jolcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.block.custom.AlchemistWorkbenchBlock;
import net.sievert.jolcraft.block.custom.CoinPressBlock;
import net.sievert.jolcraft.block.custom.JolCraftBranchBlock;
import net.sievert.jolcraft.block.custom.JolCraftFlammableRotatedPillarBlock;
import net.sievert.jolcraft.item.JolCraftItems;
import net.sievert.jolcraft.world.tree.GemTreeGrower;

import java.util.function.Supplier;

public class JolCraftBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, JolCraft.MOD_ID);


    //Dwarven Metal
    public static final RegistryObject<Block> DWARVEN_METAL_DEEPSLATE_ORE = registerBlock("dwarven_metal_deepslate_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE).strength(5.0F, 5.0F).lightLevel((p_50874_) -> {return 3;}).requiresCorrectToolForDrops(), UniformInt.of(4, 8)));

    public static final RegistryObject<Block> RAW_DWARVEN_METAL_BLOCK = registerBlock("raw_dwarven_metal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(7.0F, 7.0F).lightLevel((p_50874_) -> {return 3;}).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DWARVEN_METAL_BLOCK = registerBlock("dwarven_metal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(7.0F, 7.0F).lightLevel((p_50874_) -> {return 3;}).requiresCorrectToolForDrops()));

    //Gem Tree
    public static final RegistryObject<Block> GEM_LOG = registerBlock("gem_log",
            () -> new JolCraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GEM_WOOD = registerBlock("gem_wood",
            () -> new JolCraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STRIPPED_GEM_LOG = registerBlock("stripped_gem_log",
            () -> new JolCraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STRIPPED_GEM_WOOD = registerBlock("stripped_gem_wood",
            () -> new JolCraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GEM_LOG_CROSS = registerBlock("gem_log_cross",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD)
                    .strength(5f).requiresCorrectToolForDrops().noOcclusion()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }
            });
    public static final RegistryObject<Block> GEM_LOG_BRANCH = registerBlock("gem_log_branch",
            () -> new JolCraftBranchBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(3f).noOcclusion().requiresCorrectToolForDrops()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }
            });
    public static final RegistryObject<Block> GEM_PLANKS = registerBlock("gem_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .strength(5f)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }
            });

    public static final RegistryObject<Block> GEM_SAPLING = registerBlock("gem_sapling",
            () -> new SaplingBlock(new GemTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> DWARVEN_GEM_CLUSTER = registerBlock("dwarven_gem_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).requiresCorrectToolForDrops().strength(2F).lightLevel((p_152632_) -> {
                return 5;
            })));
    public static final RegistryObject<Block> DWARVEN_GEM_BLOCK = registerBlock("dwarven_gem_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));


    //Coin Blocks

    public static final RegistryObject<Block> COIN_BLOCK = registerBlock("coin_block",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> COIN_PRESS = registerBlock("coin_press",
            () -> new CoinPressBlock(BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE_TILES).strength(4f).noOcclusion()));

    //POIS

    public static final RegistryObject<Block> ALCHEMIST_WORKBENCH = registerBlock("alchemist_workbench",
            () -> new AlchemistWorkbenchBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(3f).noOcclusion()));
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return JolCraftItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
