package net.sievert.jolcraft.entity.client.dwarf.blacksmith;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfBlacksmith;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfBlacksmithModel extends DefaultedEntityGeoModel<DwarfBlacksmith> {

    public DwarfBlacksmithModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_blacksmith"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfBlacksmith object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfBlacksmith object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/blacksmith.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfBlacksmith animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}