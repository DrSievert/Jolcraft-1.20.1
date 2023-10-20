package net.sievert.jolcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.sievert.jolcraft.entity.blockentites.CoinPressBlockEntity;
import net.sievert.jolcraft.entity.blockentites.JolCraftBlockEntities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoinPressBlock extends BaseEntityBlock {
    public CoinPressBlock(Properties properties) {

        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WORK, false));
    }

    public static final BooleanProperty WORK = BooleanProperty.create("work");
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WORK);
    }

    /*Block Entity*/
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CoinPressBlockEntity) {
                ((CoinPressBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

   /* @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof CoinPressBlockEntity) {
                //NetworkHooks.openScreen(((ServerPlayer)pPlayer), (CoinPressBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Container provider is missing!");
            }

        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }*/

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (!(be instanceof CoinPressBlockEntity blockEntity))
            return InteractionResult.PASS;

        if (pLevel.isClientSide())
            return InteractionResult.SUCCESS;

        // open screen
        if(pPlayer instanceof ServerPlayer sPlayer) {
            sPlayer.openMenu(blockEntity);
        }

        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CoinPressBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, JolCraftBlockEntities.COIN_PRESS_STATION.get(),
                CoinPressBlockEntity::tick);

    }



}
