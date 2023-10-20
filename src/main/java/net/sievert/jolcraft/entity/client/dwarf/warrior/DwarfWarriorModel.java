package net.sievert.jolcraft.entity.client.dwarf.warrior;

import net.minecraft.resources.ResourceLocation;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.DwarfWarrior;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DwarfWarriorModel extends DefaultedEntityGeoModel<DwarfWarrior> {

    public DwarfWarriorModel() {
        super(new ResourceLocation(JolCraft.MOD_ID, "dwarf_warrior"), true);
    }

    @Override
    public ResourceLocation getModelResource(DwarfWarrior object) {
        return new ResourceLocation(JolCraft.MOD_ID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfWarrior object) {
        return new ResourceLocation(JolCraft.MOD_ID, "textures/entity/dwarf/warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfWarrior animatable) {
        return new ResourceLocation(JolCraft.MOD_ID, "animations/dwarf.animations.json");
    }


}