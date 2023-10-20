package net.sievert.jolcraft.entity.client.dwarf;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfEntity;
import net.sievert.jolcraft.entity.variant.DwarfVariant;
import software.bernie.example.client.renderer.entity.layer.CoolKidGlassesLayer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

public class DwarfRenderer extends GeoEntityRenderer<DwarfEntity> {
    public DwarfRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfModel());
        addRenderLayer(new DwarfArmorLayer(this));
        this.shadowRadius = 0.4F;
    }

    public static final Map<DwarfVariant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(DwarfVariant.class), (p_114874_) -> {
        p_114874_.put(DwarfVariant.DEFAULT, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf.png"));
        p_114874_.put(DwarfVariant.RED, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_red.png"));
        p_114874_.put(DwarfVariant.GREEN, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_green.png"));
        p_114874_.put(DwarfVariant.BLUE, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_blue.png"));
        p_114874_.put(DwarfVariant.PINK, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_pink.png"));
        p_114874_.put(DwarfVariant.PURPLE, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_purple.png"));
        p_114874_.put(DwarfVariant.WHITE, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_white.png"));
        p_114874_.put(DwarfVariant.YELLOW, new ResourceLocation(JolCraft.MOD_ID,"textures/entity/dwarf/dwarf_yellow.png"));

    });
    @Override
    public ResourceLocation getTextureLocation(DwarfEntity p_114872_) {
        return LOCATION_BY_VARIANT.get(p_114872_.getDwarfVariant());
    }
    @Override
    public void render(DwarfEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }


}