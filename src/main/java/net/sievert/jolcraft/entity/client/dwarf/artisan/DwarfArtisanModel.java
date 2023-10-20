package net.sievert.jolcraft.entity.client.dwarf.artisan;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfArtisan;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfArtisanModel extends DefaultedEntityGeoModel<DwarfArtisan> {

    public DwarfArtisanModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_artisan"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfArtisan object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfArtisan object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/artisan.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfArtisan animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}