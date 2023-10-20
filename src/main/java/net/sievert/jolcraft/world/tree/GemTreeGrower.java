package net.sievert.jolcraft.world.tree;

        import net.sievert.jolcraft.world.features.JolCraftConfiguredFeatures;
        import net.minecraft.resources.ResourceKey;
        import net.minecraft.util.RandomSource;
        import net.minecraft.world.level.block.grower.AbstractTreeGrower;
        import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
        import org.jetbrains.annotations.Nullable;

public class GemTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return JolCraftConfiguredFeatures.GEM_TREE;
    }
}