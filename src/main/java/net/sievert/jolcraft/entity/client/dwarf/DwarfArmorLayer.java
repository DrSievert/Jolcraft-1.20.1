package net.sievert.jolcraft.entity.client.dwarf;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfEntity;
import net.sievert.jolcraft.entity.variant.DwarfArmorVariant;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.Map;


public class DwarfArmorLayer extends GeoRenderLayer<DwarfEntity> {
    public static final Map<DwarfArmorVariant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(DwarfArmorVariant.class), (p_114874_) -> {
        p_114874_.put(DwarfArmorVariant.DEFAULT, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/empty.png"));
        p_114874_.put(DwarfArmorVariant.JEWELRY, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/jewelry.png"));
        p_114874_.put(DwarfArmorVariant.LEATHER, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/leather.png"));
        p_114874_.put(DwarfArmorVariant.CHAIN, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/chain.png"));
        p_114874_.put(DwarfArmorVariant.IRON, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/iron.png"));
        p_114874_.put(DwarfArmorVariant.DIAMOND, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/diamond.png"));
        p_114874_.put(DwarfArmorVariant.NETHERITE, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/netherite.png"));

    });
    public DwarfArmorLayer(GeoRenderer<DwarfEntity> entityRenderer) {
        super(entityRenderer);
    }

    // Apply texture layer to the existing geo model, and render it over the top of the existing model
    @Override
    public void render(PoseStack poseStack, DwarfEntity animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(LOCATION_BY_VARIANT.get(animatable.getDwarfArmorVariant()));

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType,
                bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }
}