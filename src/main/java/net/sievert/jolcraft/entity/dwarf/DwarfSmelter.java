package net.sievert.jolcraft.entity.dwarf;



import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sievert.jolcraft.villager.JolCraftVillagers;

import javax.annotation.Nullable;


public class DwarfSmelter extends DwarfEntity {
    public DwarfSmelter(EntityType<? extends DwarfEntity> entityType, Level level) {
        super(entityType, level);
    }
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {

        this.setVillagerData(this.getVillagerData().setProfession(JolCraftVillagers.SMELTER.get()));
        this.setVillagerData(this.getVillagerData().setLevel(2));
        return super.finalizeSpawn(levelAccessor, difficultyInstance, spawnType, spawnGroupData, compoundTag);
    }

    public void refreshBrain(ServerLevel p_35484_) {
        Brain<Villager> brain = this.getBrain();
        brain.stopAll(p_35484_, this);
        this.brain = brain.copyWithoutBehaviors();
        this.registerBrainGoals(this.getBrain());
    }

    private void registerBrainGoals(Brain<Villager> p_35425_) {
        VillagerProfession villagerprofession = this.getVillagerData().getProfession();
        if (this.isBaby()) {
            p_35425_.setSchedule(Schedule.VILLAGER_BABY);
            p_35425_.addActivity(Activity.PLAY, VillagerGoalPackages.getPlayPackage(0.5F));
        } else {
            p_35425_.setSchedule(Schedule.VILLAGER_DEFAULT);
            p_35425_.addActivityWithConditions(Activity.WORK, VillagerGoalPackages.getWorkPackage(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT)));
        }

        p_35425_.addActivity(Activity.CORE, VillagerGoalPackages.getCorePackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.REST, VillagerGoalPackages.getRestPackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.IDLE, VillagerGoalPackages.getIdlePackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.PANIC, VillagerGoalPackages.getPanicPackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.PRE_RAID, VillagerGoalPackages.getPreRaidPackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.RAID, VillagerGoalPackages.getRaidPackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.HIDE, VillagerGoalPackages.getHidePackage(villagerprofession, 0.5F));
        p_35425_.setCoreActivities(ImmutableSet.of(Activity.CORE));
        p_35425_.setDefaultActivity(Activity.IDLE);
        p_35425_.setActiveActivityIfPossible(Activity.IDLE);
        p_35425_.updateActivityFromSchedule(this.level().getDayTime(), this.level().getGameTime());
    }
    @Nullable
    @Override
    public DwarfEntity getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return null;
    }

}
