package net.sievert.jolcraft.sound;

import net.sievert.jolcraft.JolCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JolCraftSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JolCraft.MOD_ID);

    public static RegistryObject<SoundEvent> COIN_BLOCK_BREAK = registerSoundEvent("coin_block_break");
    public static RegistryObject<SoundEvent> COIN_BLOCK_STEP = registerSoundEvent("coin_block_step");
    public static RegistryObject<SoundEvent> COIN_BLOCK_PLACE = registerSoundEvent("coin_block_place");
    public static RegistryObject<SoundEvent> COIN_BLOCK_HIT = registerSoundEvent("coin_block_hit");
    public static RegistryObject<SoundEvent> COIN_BLOCK_FALL = registerSoundEvent("coin_block_fall");
    public static RegistryObject<SoundEvent> DWARF_IDLE = registerSoundEvent("dwarf_idle");
    public static RegistryObject<SoundEvent> DWARF_HIT = registerSoundEvent("dwarf_hit");
    public static RegistryObject<SoundEvent> DWARF_DEATH = registerSoundEvent("dwarf_death");
    public static RegistryObject<SoundEvent> DWARF_HAGGLE = registerSoundEvent("dwarf_haggle");
    public static RegistryObject<SoundEvent> DWARF_YES = registerSoundEvent("dwarf_yes");
    public static RegistryObject<SoundEvent> DWARF_NO = registerSoundEvent("dwarf_no");

    public static RegistryObject<SoundEvent> VILLAGER_WORK_MINER = registerSoundEvent("dwarf_miner");


    public static final ForgeSoundType COIN_BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
            JolCraftSounds.COIN_BLOCK_BREAK, JolCraftSounds.COIN_BLOCK_STEP, JolCraftSounds.COIN_BLOCK_PLACE,
            JolCraftSounds.COIN_BLOCK_HIT, JolCraftSounds.COIN_BLOCK_FALL);

    private static RegistryObject<SoundEvent>  registerSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(JolCraft.MOD_ID, soundName)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}