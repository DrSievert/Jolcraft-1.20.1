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
import net.minecraftforge.network.NetworkHooks;
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
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        // IF: Code is executing on logical client.
        if(level.isClientSide) {
            // Do nothing.
            return InteractionResult.SUCCESS;
        }

        // Get block entity at the block location.
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        // IF: block entity is matching.
        if (blockEntity instanceof CoinPressBlockEntity) {

            // Cast player to ServerPlayer.
            ServerPlayer serverPlayer = (ServerPlayer) player;

            // Cast block entity to VikingChestBlockEntity.
            CoinPressBlockEntity vikingChestBlockEntity = (CoinPressBlockEntity)blockEntity;

            // Open menu.
            NetworkHooks.openScreen(serverPlayer, vikingChestBlockEntity, blockPos);
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
