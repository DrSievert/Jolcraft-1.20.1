package net.sievert.jolcraft.entity.client.dwarf.miner;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfMiner;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfMinerModel extends DefaultedEntityGeoModel<DwarfMiner> {

    public DwarfMinerModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_miner"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfMiner object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf_miner.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfMiner object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/miner.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfMiner animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf_miner.animations.json");
    }


}