package net.sievert.jolcraft.entity.dwarf;


import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.sievert.jolcraft.block.JolCraftBlocks;
import net.sievert.jolcraft.sound.JolCraftSounds;
import net.sievert.jolcraft.villager.JolCraftVillagers;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;


public class DwarfMiner extends DwarfEntity {
    public DwarfMiner(EntityType<? extends DwarfEntity> entityType, Level level) {
        super(entityType, level);
    }
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {

        this.setVillagerData(this.getVillagerData().setProfession(JolCraftVillagers.MINER.get()));
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
        p_35425_.addActivity(Activity.IDLE, VillagerGoalPackages.getIdlePackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.PANIC, VillagerGoalPackages.getPanicPackage(villagerprofession, 0.5F));
        p_35425_.addActivity(Activity.HIDE, VillagerGoalPackages.getHidePackage(villagerprofession, 0.5F));
        p_35425_.setCoreActivities(ImmutableSet.of(Activity.CORE));
        p_35425_.setDefaultActivity(Activity.IDLE);
        p_35425_.setActiveActivityIfPossible(Activity.IDLE);
        p_35425_.updateActivityFromSchedule(this.level().getDayTime(), this.level().getGameTime());
    }

    public boolean mineanimation = false;

    public void aiStep(){
        if (isMineableArea(this.level(), this.blockPosition())) {
            if (this.random.nextInt(50) == 0) {
                this.mineanimation = true;
            }
        }
        super.aiStep();
    }

    protected boolean isMineableArea(LevelReader levelReader, BlockPos pos) {
        if(levelReader.getBlockState(pos.east()).is(Blocks.DIAMOND_ORE)) {
            return true;
        }
        if(levelReader.getBlockState(pos.west()).is(Blocks.DIAMOND_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.north()).is(Blocks.DIAMOND_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.south()).is(Blocks.DIAMOND_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.east()).is(JolCraftBlocks.DWARVEN_METAL_DEEPSLATE_ORE.get())) {
            return true;
        }
        if(levelReader.getBlockState(pos.west()).is(JolCraftBlocks.DWARVEN_METAL_DEEPSLATE_ORE.get()))    {
            return true;
        }
        if(levelReader.getBlockState(pos.north()).is(JolCraftBlocks.DWARVEN_METAL_DEEPSLATE_ORE.get()))    {
            return true;
        }
        if(levelReader.getBlockState(pos.south()).is(JolCraftBlocks.DWARVEN_METAL_DEEPSLATE_ORE.get()))    {
            return true;
        }
        if(levelReader.getBlockState(pos.east()).is(Blocks.GOLD_ORE)) {
            return true;
        }
        if(levelReader.getBlockState(pos.west()).is(Blocks.GOLD_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.north()).is(Blocks.GOLD_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.south()).is(Blocks.GOLD_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.east()).is(Blocks.IRON_ORE)) {
            return true;
        }
        if(levelReader.getBlockState(pos.west()).is(Blocks.IRON_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.north()).is(Blocks.IRON_ORE))    {
            return true;
        }
        if(levelReader.getBlockState(pos.south()).is(Blocks.IRON_ORE))    {
            return true;
        }
        return false;
    }

    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
        controllers.add(new AnimationController(this, "miningcontroller",
                0, this::miningpredicate));

    }


    protected PlayState miningpredicate(AnimationState animationState) {
        if (this.mineanimation && animationState.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            animationState.getController().forceAnimationReset();
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.dwarf.mine", Animation.LoopType.PLAY_ONCE));
            this.level().addParticle(ParticleTypes.POOF, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), JolCraftSounds.VILLAGER_WORK_MINER.get(), this.getSoundSource(), 0.01F + 0.02F * this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, true);
            this.mineanimation = false;
        }
        return PlayState.CONTINUE;
    }


    private PlayState predicate(AnimationState animationState) {
        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.dwarf.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        animationState.getController().setAnimation(RawAnimation.begin().then("animation.dwarf.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }
    protected void populateDefaultEquipmentSlots(RandomSource p_219171_, DifficultyInstance p_219172_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_PICKAXE));
    }

    @Nullable
    @Override
    public DwarfEntity getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return null;
    }

}
