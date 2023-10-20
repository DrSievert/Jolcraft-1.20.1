package net.sievert.jolcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;


@Mod.EventBusSubscriber(modid = JolCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JolCraftCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JolCraft.MOD_ID);

    public static RegistryObject<CreativeModeTab> JOLCRAFT_TAB = CREATIVE_MODE_TABS.register("jolcraft_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(JolCraftItems.COIN.get()))
            .title(Component.literal("JolCraft"))
            .withLabelColor(0x00FF00)
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }


}