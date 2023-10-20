package net.sievert.jolcraft.entity.client.dwarf.alchemist;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfAlchemist;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfAlchemistModel extends DefaultedEntityGeoModel<DwarfAlchemist> {

    public DwarfAlchemistModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_alchemist"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfAlchemist object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfAlchemist object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/alchemist.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfAlchemist animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}