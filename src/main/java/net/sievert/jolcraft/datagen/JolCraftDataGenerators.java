package net.sievert.jolcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.sievert.jolcraft.JolCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = JolCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JolCraftDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //generator.addProvider(true, new JolCraftRecipeProvider(packOutput));
       generator.addProvider(true, JolCraftLootTableProvider.create(packOutput));
       generator.addProvider(true, new JolCraftBlockStateProvider(packOutput, existingFileHelper));
       generator.addProvider(true, new JolCraftItemModelProvider(packOutput, existingFileHelper));

    }
}