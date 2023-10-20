package net.sievert.jolcraft.entity.dwarf;

import com.google.common.collect.ImmutableSet;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.sievert.jolcraft.entity.JolCraftEntities;
import net.sievert.jolcraft.entity.variant.DwarfArmorVariant;
import net.sievert.jolcraft.entity.variant.DwarfVariant;
import net.sievert.jolcraft.sound.JolCraftSounds;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import javax.annotation.Nullable;
import java.util.Set;


public class DwarfEntity extends Villager implements GeoEntity {

    private int foodLevel;
    private static final Set<Item> WANTED_ITEMS = ImmutableSet.of(Items.BREAD, Items.POTATO, Items.CARROT, Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT, Items.BEETROOT_SEEDS);


    //Basic

    public DwarfEntity(EntityType<? extends DwarfEntity> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, 8.0F);

    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.6).add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.ATTACK_DAMAGE, 2.0D).build();
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    //Sounds
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            return this.isTrading() ? JolCraftSounds.DWARF_HAGGLE.get() : JolCraftSounds.DWARF_IDLE.get();
        }
    }
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return JolCraftSounds.DWARF_HIT.get();
    }
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return JolCraftSounds.DWARF_DEATH.get();
    }
    @Override
    public SoundEvent getNotifyTradeSound() {
        return JolCraftSounds.DWARF_YES.get();
    }
    @Override
    protected SoundEvent getTradeUpdatedSound(boolean p_35323_) {
        return p_35323_ ? JolCraftSounds.DWARF_YES.get() : JolCraftSounds.DWARF_NO.get();
    }
    @Override
    public void playCelebrateSound() {
        this.playSound(JolCraftSounds.DWARF_YES.get(), this.getSoundVolume(), this.getVoicePitch());
    }
    @Nullable
    private void setUnhappy() {
        this.setUnhappyCounter(40);
        if (!this.level().isClientSide()) {
            this.playSound(JolCraftSounds.DWARF_NO.get(), this.getSoundVolume(), this.getVoicePitch());
        }

    }

    //Interaction

    @Override
    public InteractionResult mobInteract(Player p_35472_, InteractionHand p_35473_) {
        ItemStack itemstack = p_35472_.getItemInHand(p_35473_);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.isTrading() && !this.isSleeping() && !p_35472_.isSecondaryUseActive()) {
            if (this.isBaby()) {
                this.setUnhappy();
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                boolean flag = this.getOffers().isEmpty();
                if (p_35473_ == InteractionHand.MAIN_HAND) {
                    if (flag && !this.level().isClientSide) {
                        this.setUnhappy();
                    }

                    p_35472_.awardStat(Stats.TALKED_TO_VILLAGER);
                }

                if (flag) {
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                } else {
                    if (!this.level().isClientSide && !this.offers.isEmpty()) {
                        this.startTrading(p_35472_);
                    }

                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }
        } else {
            return super.mobInteract(p_35472_, p_35473_);
        }
    }

    private void startTrading(Player p_35537_) {
        this.updateSpecialPrices(p_35537_);
        this.setTradingPlayer(p_35537_);
        this.openTradingScreen(p_35537_, this.getDisplayName(), this.getVillagerData().getLevel());
    }
    private void updateSpecialPrices(Player p_35541_) {
        int i = this.getPlayerReputation(p_35541_);
        if (i != 0) {
            for(MerchantOffer merchantoffer : this.getOffers()) {
                merchantoffer.addToSpecialPriceDiff(-Mth.floor((float)i * merchantoffer.getPriceMultiplier()));
            }
        }

        if (p_35541_.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)) {
            MobEffectInstance mobeffectinstance = p_35541_.getEffect(MobEffects.HERO_OF_THE_VILLAGE);
            int k = mobeffectinstance.getAmplifier();

            for(MerchantOffer merchantoffer1 : this.getOffers()) {
                double d0 = 0.3D + 0.0625D * (double)k;
                int j = (int)Math.floor(d0 * (double)merchantoffer1.getBaseCostA().getCount());
                merchantoffer1.addToSpecialPriceDiff(-Math.max(j, 1));
            }
        }

    }

    //Animations


    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
    }

    private PlayState predicate(AnimationState animationState) {
        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.dwarf.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().then("animation.dwarf.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    // Variants

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(DwarfEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_ARMOR_VARIANT = SynchedEntityData.defineId(DwarfEntity.class, EntityDataSerializers.INT);

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(DATA_ID_TYPE_ARMOR_VARIANT, 0);

    }
    public void addAdditionalSaveData(CompoundTag p_30716_) {
        super.addAdditionalSaveData(p_30716_);
        p_30716_.putInt("DwarfVariant", this.getTypeVariant());
        p_30716_.putInt("DwarfArmorVariant", this.getArmorTypeVariant());

    }
    public void readAdditionalSaveData(CompoundTag p_30711_) {
        super.readAdditionalSaveData(p_30711_);
        this.setTypeVariant(p_30711_.getInt("DwarfVariant"));
        this.setArmorTypeVariant(p_30711_.getInt("DwarfArmorVariant"));

    }

    private void setTypeVariant(int p_30737_) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_30737_);
    }
    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }
    private void setVariant(DwarfVariant p_30700_) {
        this.setTypeVariant(p_30700_.getId() & 255);
    }
    public DwarfVariant getDwarfVariant() {
        return DwarfVariant.byId(this.getTypeVariant() & 255);
    }

    private void setArmorTypeVariant(int p_30737_) {
        this.entityData.set(DATA_ID_TYPE_ARMOR_VARIANT, p_30737_);
    }
    private int getArmorTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_ARMOR_VARIANT);
    }
    private void setArmorVariant(DwarfArmorVariant p_30700_) {
        this.setArmorTypeVariant(p_30700_.getId() & 255);
    }
    public DwarfArmorVariant getDwarfArmorVariant() {
        return DwarfArmorVariant.byId(this.getArmorTypeVariant() & 255);
    }

    private void setVariantAndMarkings(DwarfVariant p_30700_, DwarfArmorVariant p_30701_) {
        this.setTypeVariant(p_30700_.getId() & 255 | p_30701_.getId() & 255);
    }



    //Spawning and Breeding

    public boolean canBreed() {
        return this.foodLevel + this.countFoodPointsInInventory() >= 12 && !this.isSleeping() && this.getAge() == 0;
    }

    private int countFoodPointsInInventory() {
        SimpleContainer simplecontainer = this.getInventory();
        return FOOD_POINTS.entrySet().stream().mapToInt((p_186300_) -> {
            return simplecontainer.countItem(p_186300_.getKey()) * p_186300_.getValue();
        }).sum();
    }

    private boolean hungry() {
        return this.foodLevel < 12;
    }

    private void eatUntilFull() {
        if (this.hungry() && this.countFoodPointsInInventory() != 0) {
            for(int i = 0; i < this.getInventory().getContainerSize(); ++i) {
                ItemStack itemstack = this.getInventory().getItem(i);
                if (!itemstack.isEmpty()) {
                    Integer integer = FOOD_POINTS.get(itemstack.getItem());
                    if (integer != null) {
                        int j = itemstack.getCount();

                        for(int k = j; k > 0; --k) {
                            this.foodLevel += integer;
                            this.getInventory().removeItem(i, 1);
                            if (!this.hungry()) {
                                return;
                            }
                        }
                    }
                }
            }

        }
    }

    public void eatAndDigestFood() {
        this.eatUntilFull();
        this.digestFood(12);
    }

    private void digestFood(int pQty) {
        this.foodLevel -= pQty;
    }

    public boolean wantsToPickUp(ItemStack pStack) {
        Item item = pStack.getItem();
        return (WANTED_ITEMS.contains(item) || this.getVillagerData().getProfession().requestedItems().contains(item)) && this.getInventory().canAddItem(pStack);
    }

    public boolean hasExcessFood() {
        return this.countFoodPointsInInventory() >= 24;
    }

    public boolean wantsMoreFood() {
        return this.countFoodPointsInInventory() < 12;
    }


    public static boolean canSpawn(EntityType<DwarfEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkMobSpawnRules(entityType, level, spawnType, pos, random) && pos.getY() < 60 && isBrightEnoughToSpawn(level, pos) && level.getBlockState(pos).is(Blocks.COBBLED_DEEPSLATE);
    }

    protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter p_186210_, BlockPos p_186211_) {
        return p_186210_.getRawBrightness(p_186211_, 0) > 8 ;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30703_, DifficultyInstance p_30704_, MobSpawnType p_30705_, @Nullable SpawnGroupData p_30706_, @Nullable CompoundTag p_30707_) {
        DwarfVariant variant = Util.getRandom(DwarfVariant.values(), this.random);
        DwarfArmorVariant armorvariant = Util.getRandom(DwarfArmorVariant.values(), this.random);
        setVariant(variant);
        setArmorVariant(armorvariant);
        return super.finalizeSpawn(p_30703_, p_30704_, p_30705_, p_30706_, p_30707_);
    }
    @Nullable
    @Override
    public DwarfEntity getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        DwarfEntity entity = (DwarfEntity) ageableMob;
        DwarfEntity entity1 = JolCraftEntities.DWARF.get().create(level);
        DwarfVariant variant;
        variant = Util.getRandom(DwarfVariant.values(), this.random);
        DwarfArmorVariant armorvariant;
        armorvariant = Util.getRandom(DwarfArmorVariant.values(), this.random);
        entity1.setVariantAndMarkings(variant, armorvariant);

        return entity1;

    }


}
