package net.sievert.jolcraft.entity.client.dwarf.enchanter;


import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.DwarfEnchanter;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfEnchanterRenderer extends GeoEntityRenderer<DwarfEnchanter> {
    public DwarfEnchanterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfEnchanterModel());
        this.shadowRadius = 0.4F;
    }

}