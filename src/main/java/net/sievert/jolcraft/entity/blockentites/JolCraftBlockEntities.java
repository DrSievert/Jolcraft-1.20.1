package net.sievert.jolcraft.entity.blockentites;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.block.JolCraftBlocks;

public class JolCraftBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JolCraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<CoinPressBlockEntity>> COIN_PRESS_STATION = BLOCK_ENTITIES.register("coin_press_station", () ->
            BlockEntityType.Builder.of(CoinPressBlockEntity::new,
                    JolCraftBlocks.COIN_PRESS.get()).build(null));
    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}

