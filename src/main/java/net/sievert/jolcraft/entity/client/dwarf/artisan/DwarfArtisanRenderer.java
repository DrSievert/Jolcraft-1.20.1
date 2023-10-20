package net.sievert.jolcraft.entity.client.dwarf.artisan;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.DwarfArtisan;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfArtisanRenderer extends GeoEntityRenderer<DwarfArtisan> {
    public DwarfArtisanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfArtisanModel());
        this.shadowRadius = 0.4F;
    }

}