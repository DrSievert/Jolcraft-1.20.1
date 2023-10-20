package net.sievert.jolcraft.entity.client.spirit;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.spirit.SpiritFoxEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class SpiritFoxRenderer extends GeoEntityRenderer<SpiritFoxEntity> {
    public SpiritFoxRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiritFoxModel());
        this.shadowRadius = 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(SpiritFoxEntity instance) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/spirit/spirit_fox.png");
    }

    @Override
    public RenderType getRenderType(SpiritFoxEntity entity, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }

}