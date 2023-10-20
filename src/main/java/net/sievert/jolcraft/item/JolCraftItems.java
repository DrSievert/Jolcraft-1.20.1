package net.sievert.jolcraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.entity.JolCraftEntities;
import net.sievert.jolcraft.JolCraft;

public class JolCraftItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JolCraft.MOD_ID);

    //Dwarven Metal

    public static final RegistryObject<Item> UNREFINED_DWARVEN_METAL = ITEMS.register("unrefined_dwarven_metal",
            () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> RAW_DWARVEN_METAL = ITEMS.register("raw_dwarven_metal",
            () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> DWARVEN_METAL_INGOT = ITEMS.register("dwarven_metal_ingot",
            () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.RARE)));

    //Dwarven Gem

    public static final RegistryObject<Item> DWARVEN_GEM_SHARD = ITEMS.register("dwarven_gem_shard",
            () -> new Item(new Item.Properties()));

    //Coin Items
    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties().stacksTo(999)));
    public static final RegistryObject<Item> COIN_MOULD = ITEMS.register("coin_mould",
            () -> new Item(new Item.Properties().stacksTo(1)));

    //Spawn Eggs
    public static final RegistryObject<Item> SPIRIT_FOX_SPAWN_EGG = ITEMS.register("spirit_fox_spawn_egg",
            () -> new ForgeSpawnEggItem(JolCraftEntities.SPIRIT_FOX, 0xd1f0ff, 0xd7ffec,
                    new Item.Properties()));
    public static final RegistryObject<Item> DWARF_SPAWN_EGG = ITEMS.register("dwarf_spawn_egg",
            () -> new ForgeSpawnEggItem(JolCraftEntities.DWARF, 0x461c00, 0x9b735e,
                    new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
