package net.sievert.jolcraft.entity.client.dwarf.smelter;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.DwarfSmelter;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfSmelterRenderer extends GeoEntityRenderer<DwarfSmelter> {
    public DwarfSmelterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfSmelterModel());
        this.shadowRadius = 0.4F;
    }

}