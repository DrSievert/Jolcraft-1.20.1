package net.sievert.jolcraft.entity.client.dwarf.smelter;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfSmelter;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfSmelterModel extends DefaultedEntityGeoModel<DwarfSmelter> {

    public DwarfSmelterModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_smelter"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfSmelter object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfSmelter object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/smelter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfSmelter animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}