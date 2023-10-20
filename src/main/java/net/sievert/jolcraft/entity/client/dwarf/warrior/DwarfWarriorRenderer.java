package net.sievert.jolcraft.entity.client.dwarf.warrior;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.*;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfWarriorRenderer extends GeoEntityRenderer<DwarfWarrior> {
    public DwarfWarriorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfWarriorModel());
        this.shadowRadius = 0.4F;
    }

}