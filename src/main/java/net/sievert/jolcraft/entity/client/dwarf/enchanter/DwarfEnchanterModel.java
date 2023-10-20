package net.sievert.jolcraft.entity.client.dwarf.enchanter;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfEnchanter;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfEnchanterModel extends DefaultedEntityGeoModel<DwarfEnchanter> {

    public DwarfEnchanterModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_enchanter"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfEnchanter object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfEnchanter object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/enchanter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfEnchanter animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}