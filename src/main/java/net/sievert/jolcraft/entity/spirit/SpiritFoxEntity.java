package net.sievert.jolcraft.entity.spirit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class SpiritFoxEntity extends Animal implements GeoEntity {
    public SpiritFoxEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }
        @Override
        protected void registerGoals() {
            this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
            this.goalSelector.addGoal(1, new SpiritFoxGoToTemple(this, 1.5D));
            this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
            this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
            super.registerGoals();
        }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.9).add(Attributes.MOVEMENT_SPEED, (double)0.2F).build();
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.PHANTOM_FLAP, 0.4F, 0.55F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.FOX_SCREECH;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

   /*protected float getSoundVolume() {
        return 0.5F;
    }*/

    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
    }
    private PlayState predicate(AnimationState animationState) {
        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.spiritfox.moving", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().then("animation.spiritfox.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    static class SpiritFoxGoToTemple extends MoveToBlockGoal {
        private final SpiritFoxEntity spirit_fox;

        SpiritFoxGoToTemple(SpiritFoxEntity entity, double p_33956_) {
            super(entity, p_33956_, 8, 2);
            this.spirit_fox = entity;
        }

        public BlockPos getMoveToTarget() {
            return this.blockPos;
        }

        public boolean canContinueToUse() {
            return this.isValidTarget(this.spirit_fox.level(), this.blockPos);
        }

        public boolean canUse(LevelReader levelReader, BlockPos pos) {
            return !this.spirit_fox.getBlockStateOn().is(Blocks.STONE_BRICKS) && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 10 == 0;
        }

        protected boolean isValidTarget(LevelReader levelReader, BlockPos pos) {
            return levelReader.getBlockState(pos).is(Blocks.STONE_BRICKS) && levelReader.getBlockState(pos.above()).isPathfindable(levelReader, pos, PathComputationType.LAND);
        }
    }
    protected PathNavigation createNavigation(Level level) {
        return new SpiritFoxEntity.SpiritFoxPathNavigation(this, level);
    }
    static class SpiritFoxPathNavigation extends GroundPathNavigation {
        SpiritFoxPathNavigation(SpiritFoxEntity entity, Level level) {
            super(entity, level);
        }

        protected PathFinder createPathFinder(int i) {
            this.nodeEvaluator = new WalkNodeEvaluator();
            this.nodeEvaluator.setCanPassDoors(true);
            return new PathFinder(this.nodeEvaluator, i);
        }

        public boolean isStableDestination(BlockPos pos) {
            return this.level.getBlockState(pos).is(Blocks.STONE_BRICKS) || super.isStableDestination(pos);
        }
    }
    public void aiStep(){
        super.aiStep();
        if (this.level().isClientSide) {
            if (this.random.nextInt(70) == 0) {
                this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.SOUL_ESCAPE, this.getSoundSource(), 0.4F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
                this.level().addParticle(ParticleTypes.SCULK_SOUL, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
    }
    @Override
    public boolean canBeLeashed(Player p_21418_) {
        return false;
    }

}
