package net.sievert.jolcraft.entity.client.dwarf.alchemist;


import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.DwarfAlchemist;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfAlchemistRenderer extends GeoEntityRenderer<DwarfAlchemist> {
    public DwarfAlchemistRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfAlchemistModel());
        this.shadowRadius = 0.4F;
    }

}