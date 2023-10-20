package net.sievert.jolcraft.entity.client.dwarf.miner;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.sievert.jolcraft.entity.dwarf.DwarfMiner;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfMinerRenderer extends GeoEntityRenderer<DwarfMiner> {
    public DwarfMinerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DwarfMinerModel());
        this.shadowRadius = 0.4F;
    }

}