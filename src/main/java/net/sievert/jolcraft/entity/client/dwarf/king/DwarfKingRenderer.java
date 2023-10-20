package net.sievert.jolcraft.entity.client.dwarf.king;


import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.client.dwarf.alchemist.DwarfAlchemistModel;
import net.sievert.jolcraft.entity.dwarf.DwarfAlchemist;
import net.sievert.jolcraft.entity.dwarf.DwarfKing;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfKingRenderer extends GeoEntityRenderer<DwarfKing> {
    public DwarfKingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfKingModel());
        this.shadowRadius = 0.4F;
    }

}