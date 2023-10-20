package net.sievert.jolcraft.datagen;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.sievert.jolcraft.JolCraft;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public class JolCraftChestLootTables implements LootTableSubProvider{
         @Override
        public void generate(@Nonnull BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            {consumer.accept(location("nibb"), LootTable.lootTable()
                    .withPool(pool(number(2,3))
                            .add(lootItem(Items.DIAMOND, 2, one()))
                            .add(lootItem(Items.IRON_AXE, 2, one()))
                            .add(lootItem(Items.IRON_SWORD, 3, one()))
                            .add(lootItem(Items.SHIELD, 3, one()))
                            .add(lootItem(Items.BOW, 3, one()))
                            .add(lootItem(Items.CROSSBOW, 3, one()))
                            .add(lootItem(Items.IRON_HELMET, 2, one()))
                            .add(lootItem(Items.IRON_CHESTPLATE, 2, one()))
                            .add(lootItem(Items.IRON_LEGGINGS, 2, one()))
                            .add(lootItem(Items.IRON_BOOTS, 2, one()))
                            .add(lootItem(Items.CHAINMAIL_HELMET, 3, one()))
                            .add(lootItem(Items.CHAINMAIL_CHESTPLATE, 3, one()))
                            .add(lootItem(Items.CHAINMAIL_LEGGINGS, 3, one()))
                            .add(lootItem(Items.CHAINMAIL_BOOTS, 3, one()))
                    ).withPool(pool(number(5, 11))
                            .add(lootItem(Items.IRON_NUGGET, 7, number(1,3)))
                            .add(lootItem(Items.ARROW, 6, number(1,3)))
                            .add(lootItem(Items.STICK, 9, number(1,2)))
                            .add(lootItem(Items.STRING, 8, number(1,2)))
                            .add(lootItem(Items.COBBLESTONE, 7, number(1,2)))
                            .add(lootItem(Items.ROTTEN_FLESH, 6, one()))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 3, one()))
                            .add(lootItem(Items.IRON_INGOT, 3, one()))
                            .add(lootItem(Items.CHAIN, 5, one()))));

                consumer.accept(location("nibba"), LootTable.lootTable()
                        .withPool(pool(number(8,14))
                                .add(lootItem(Items.BOOK, 2, one()))
                                .add(lootItem(Items.WRITABLE_BOOK, 2, one()))
                                .add(lootItem(Items.WHITE_WOOL, 3, one()))
                                .add(lootItem(Items.STRING, 3, one()))
                                .add(lootItem(Items.COBWEB, 3, one()))
                                .add(lootItem(Items.NAME_TAG, 3, one()))
                                .add(lootItem(Items.EXPERIENCE_BOTTLE, 2, one()))
                                .add(lootItem(Items.AMETHYST_SHARD, 1, one()))
                                .add(lootItem(Items.MAP, 2, one())))
                        .withPool(pool(number(0,1))
                                .add(lootItem(Items.MUSIC_DISC_STRAD, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_STAL, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_MELLOHI, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_MALL, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_FAR, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_CHIRP, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_CAT, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_BLOCKS, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_WARD, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_13, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_11, 1, one()))
                                .add(lootItem(Items.MUSIC_DISC_WAIT, 1, one()))));
            }
        }

        private LootPoolEntryContainer.Builder<?> lootItem(Item item, int weight, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
        }
        private LootPoolEntryContainer.Builder<?> enchantedLootItem(Item item, int weight, NumberProvider enchant, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(EnchantWithLevelsFunction.enchantWithLevels(enchant));
        }
        private LootPoolEntryContainer.Builder<?> enchantedLootItem(Item item, int weight, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(EnchantRandomlyFunction.randomApplicableEnchantment());
        }
        private LootPoolEntryContainer.Builder<?> suspiciousStew(int weight, NumberProvider amount){
            return LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetStewEffectFunction.stewEffect().withEffect(MobEffects.NIGHT_VISION, number(7, 10)).withEffect(MobEffects.JUMP, number(7, 10)).withEffect(MobEffects.WEAKNESS, number(6, 8)).withEffect(MobEffects.BLINDNESS, number(5, 7)).withEffect(MobEffects.POISON, number(10, 20)).withEffect(MobEffects.SATURATION, number(7, 10)));
        }
        private LootPoolEntryContainer.Builder<?> potion(int weight, Potion potion, NumberProvider amount){
            return LootItem.lootTableItem(Items.POTION).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetPotionFunction.setPotion(potion));
        }

        private NumberProvider one() {return number(1);}
        private NumberProvider number(int amount) {return ConstantValue.exactly(amount);}
        private NumberProvider number(int minAmount, int maxAmount) {return UniformGenerator.between(minAmount, maxAmount);}
        private LootPool.Builder pool(NumberProvider rolls) {return LootPool.lootPool().setRolls(rolls);}

        private static ResourceLocation location(String name) {return new ResourceLocation(JolCraft.MOD_ID, "chests/" + name);}
    }