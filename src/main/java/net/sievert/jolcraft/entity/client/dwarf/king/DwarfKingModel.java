package net.sievert.jolcraft.entity.client.dwarf.king;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfAlchemist;
import net.sievert.jolcraft.entity.dwarf.DwarfKing;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfKingModel extends DefaultedEntityGeoModel<DwarfKing> {

    public DwarfKingModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_king"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfKing object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfKing object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/king.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfKing animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}