package net.sievert.jolcraft.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.sievert.jolcraft.block.JolCraftBlocks;
import net.sievert.jolcraft.block.custom.JolCraftBranchBlock;

import java.util.Random;

public class GemTree extends Feature<NoneFeatureConfiguration> {
    public GemTree(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Random rand = new Random();
        boolean spawnblock = false;
        int maxheight = 4;
        int randomheight = 1 + rand.nextInt(2);

        BlockPos startpos = new BlockPos(context.origin());
        BlockPos topheight = startpos.above(randomheight+1);
        BlockPos.MutableBlockPos trunkpos = new BlockPos.MutableBlockPos().set(context.origin());
        BlockPos.MutableBlockPos mutablestartpos = new BlockPos.MutableBlockPos().set(context.origin());
        BlockPos.MutableBlockPos mutablestartposN = new BlockPos.MutableBlockPos().set(context.origin().north());
        BlockPos.MutableBlockPos mutablestartposE = new BlockPos.MutableBlockPos().set(context.origin().east());
        BlockPos.MutableBlockPos mutablestartposW = new BlockPos.MutableBlockPos().set(context.origin().west());
        BlockPos.MutableBlockPos mutablestartposS = new BlockPos.MutableBlockPos().set(context.origin().south());

        BlockState mutablestartstate = context.level().getBlockState(mutablestartpos);
        BlockState mutablestartstateN = context.level().getBlockState(mutablestartposN);
        BlockState mutablestartstateE = context.level().getBlockState(mutablestartposE);
        BlockState mutablestartstateW = context.level().getBlockState(mutablestartposW);
        BlockState mutablestartstateS = context.level().getBlockState(mutablestartposS);


        for(int i = -1; i <= maxheight; i++){
            mutablestartpos.move(Direction.UP);
            mutablestartposN.move(Direction.UP);
            mutablestartposE.move(Direction.UP);
            mutablestartposW.move(Direction.UP);
            mutablestartposS.move(Direction.UP);

            if(mutablestartstate.isAir() && mutablestartstateN.isAir() && mutablestartstateE.isAir() && mutablestartstateW.isAir() && mutablestartstateS.isAir()){
                spawnblock = true;
            }
        }


        if(spawnblock){
            context.level().setBlock(startpos, JolCraftBlocks.GEM_LOG.get().defaultBlockState(), 3);
            context.level().setBlock(startpos.north(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.NORTH), 3);
            context.level().setBlock(startpos.east(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.EAST), 3);
            context.level().setBlock(startpos.south(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.SOUTH), 3);
            context.level().setBlock(startpos.west(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.WEST), 3);

            for(int i = 0; i <= randomheight; i++){
                trunkpos.move(Direction.UP);
                context.level().setBlock(trunkpos, JolCraftBlocks.GEM_LOG.get().defaultBlockState(), 3);
            }

            context.level().setBlock(topheight, JolCraftBlocks.GEM_LOG_CROSS.get().defaultBlockState(), 3);

            context.level().setBlock(topheight.north(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.NORTH), 3);
            context.level().setBlock(topheight.east(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.EAST), 3);
            context.level().setBlock(topheight.south(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.SOUTH), 3);
            context.level().setBlock(topheight.west(), JolCraftBlocks.GEM_LOG_BRANCH.get().defaultBlockState().setValue(JolCraftBranchBlock.FACING, Direction.WEST), 3);


            int clusterchance1 = rand.nextInt(2);
            int clusterchance2 = rand.nextInt(2);
            int clusterchance3 = rand.nextInt(2);
            int clusterchance4 = rand.nextInt(2);

            if(clusterchance1==0){
                context.level().setBlock(topheight.north().below(), JolCraftBlocks.DWARVEN_GEM_CLUSTER.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, Direction.DOWN), 3);
            };
            if(clusterchance2==0){
                context.level().setBlock(topheight.east().below(), JolCraftBlocks.DWARVEN_GEM_CLUSTER.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, Direction.DOWN), 3);
            };
            if(clusterchance3==0){
                context.level().setBlock(topheight.south().below(), JolCraftBlocks.DWARVEN_GEM_CLUSTER.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, Direction.DOWN), 3);
            };
            if(clusterchance4==0){
                context.level().setBlock(topheight.west().below(), JolCraftBlocks.DWARVEN_GEM_CLUSTER.get().defaultBlockState().setValue(AmethystClusterBlock.FACING, Direction.DOWN), 3);
            }
            return true;
        }
        return false;
    }

}
