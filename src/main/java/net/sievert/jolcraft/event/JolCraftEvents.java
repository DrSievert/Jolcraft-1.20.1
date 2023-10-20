package net.sievert.jolcraft.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.entity.dwarf.*;
import net.sievert.jolcraft.entity.JolCraftEntities;
import net.sievert.jolcraft.entity.spirit.SpiritFoxEntity;
import net.sievert.jolcraft.item.JolCraftItems;
import net.sievert.jolcraft.villager.JolCraftVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.List;

public class JolCraftEvents {
    @Mod.EventBusSubscriber(modid = JolCraft.MOD_ID)

    public static class ForgeEvents {

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                SpawnPlacements.register(JolCraftEntities.DWARF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, DwarfEntity::canSpawn);
            });
        }
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            int Novice = 1;
            int Apprentice = 2;
            int Journeyman = 3;
            int Expert = 4;
            int Master = 5;


            //Vanilla

            if (event.getType() == VillagerProfession.TOOLSMITH) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(JolCraftItems.COIN_MOULD.get(), 1);
                trades.get(Novice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 3),
                        stack, 3, 8, 0.02F));
            }

            //Vanilla Addons

            if (event.getType() == JolCraftVillagers.BANKER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(JolCraftItems.COIN.get(), 3);
                trades.get(Novice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.GOLD_INGOT, 30),
                        stack, 5, 6, 0.02F));
            }
            if (event.getType() == JolCraftVillagers.BANKER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(JolCraftItems.COIN.get(), 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.GOLD_INGOT, 1),
                        stack, 10, 15, 0.02F));
            }

            //Alchemist

            if (event.getType() == JolCraftVillagers.ALCHEMIST.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.SPIDER_EYE, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //Artisan

            if (event.getType() == JolCraftVillagers.ARTISAN.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.AMETHYST_SHARD, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //Blacksmith

            if (event.getType() == JolCraftVillagers.BLACKSMITH.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.NETHERITE_INGOT, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //Enchanter

            if (event.getType() == JolCraftVillagers.ENCHANTER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.EXPERIENCE_BOTTLE, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //Smelter

            if (event.getType() == JolCraftVillagers.SMELTER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.OBSIDIAN, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //Warrior

            if (event.getType() == JolCraftVillagers.WARRIOR.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.GOAT_HORN, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //Miner

            if (event.getType() == JolCraftVillagers.MINER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Blocks.DIAMOND_ORE, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }

            //King

            if (event.getType() == JolCraftVillagers.KING.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.EMERALD, 1);
                trades.get(Apprentice).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(JolCraftItems.COIN.get(), 1),
                        stack, 10, 15, 0.02F));
            }




        }

       @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
               if (event.getEntity() instanceof SpiritFoxEntity) {
                   if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY) || event.getSource().isCreativePlayer()) {
                       event.setCanceled(false);
                   } else {
                       event.setCanceled(true);


                       if (event.getSource().getEntity() instanceof LivingEntity) {
                           event.getSource().getEntity().hurt(event.getEntity().damageSources().thorns(event.getEntity()), 3F);
                           ((LivingEntity) event.getSource().getEntity()).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
                       }
                   }
               }
           }

        @SubscribeEvent
        public static void onLivingConvert(LivingConversionEvent event)
        {
                if(event.getEntity() instanceof DwarfEntity){
                    event.setCanceled(true);
                }
            }

        }
        

    @Mod.EventBusSubscriber(modid = JolCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class JolCraftEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(JolCraftEntities.SPIRIT_FOX.get(), SpiritFoxEntity.setAttributes());
            event.put(JolCraftEntities.DWARF.get(), DwarfEntity.setAttributes());
            event.put(JolCraftEntities.DWARF_ALCHEMIST.get(), DwarfAlchemist.setAttributes());
            event.put(JolCraftEntities.DWARF_ARTISAN.get(), DwarfArtisan.setAttributes());
            event.put(JolCraftEntities.DWARF_BLACKSMITH.get(), DwarfBlacksmith.setAttributes());
            event.put(JolCraftEntities.DWARF_ENCHANTER.get(), DwarfEnchanter.setAttributes());
            event.put(JolCraftEntities.DWARF_SMELTER.get(), DwarfSmelter.setAttributes());
            event.put(JolCraftEntities.DWARF_WARRIOR.get(), DwarfWarrior.setAttributes());
            event.put(JolCraftEntities.DWARF_MINER.get(), DwarfMiner.setAttributes());
            event.put(JolCraftEntities.DWARF_KING.get(), DwarfKing.setAttributes());

        }
    }


}