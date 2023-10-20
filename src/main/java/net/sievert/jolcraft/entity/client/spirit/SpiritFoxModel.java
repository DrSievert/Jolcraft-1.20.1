package net.sievert.jolcraft.entity.client.spirit;

import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.spirit.SpiritFoxEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SpiritFoxModel extends DefaultedEntityGeoModel<SpiritFoxEntity> {

    public SpiritFoxModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "spirit_fox"), true);
    }


    @Override
    public ResourceLocation getModelResource(SpiritFoxEntity object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/spirit_fox.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiritFoxEntity object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/spirit/spirit_fox.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiritFoxEntity animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/spirit_fox.animations.json");
    }


}