package net.sievert.jolcraft.entity.client.dwarf;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfModel extends DefaultedEntityGeoModel<DwarfEntity> {

    public DwarfModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfEntity object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfEntity object) {
        return DwarfRenderer.LOCATION_BY_VARIANT.get(object.getDwarfVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfEntity animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }

}