package net.sievert.jolcraft.world.features;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;

public class JolCraftFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JolCraft.MOD_ID);
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GEM_TREE = FEATURES.register("gem_tree", () -> new GemTree(NoneFeatureConfiguration.CODEC));
    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

}
