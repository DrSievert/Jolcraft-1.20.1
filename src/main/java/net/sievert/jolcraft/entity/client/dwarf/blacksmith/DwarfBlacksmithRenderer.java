package net.sievert.jolcraft.entity.client.dwarf.blacksmith;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.DwarfBlacksmith;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfBlacksmithRenderer extends GeoEntityRenderer<DwarfBlacksmith> {
    public DwarfBlacksmithRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfBlacksmithModel());
        this.shadowRadius = 0.4F;
    }

}