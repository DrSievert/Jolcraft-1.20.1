package net.sievert.jolcraft.world.processors;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sievert.jolcraft.JolCraft;

import java.util.function.Supplier;


public final class JolCraftProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, JolCraft.MOD_ID);
    public static final RegistryObject<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = registerProcessor("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);

    //goofy but needed
    public static <P extends StructureProcessor> RegistryObject<StructureProcessorType<P>> registerProcessor(String name, Supplier<StructureProcessorType<P>> processor) {
        return STRUCTURE_PROCESSORS.register(name, processor);
    }

    public static void register(IEventBus eventBus){
        STRUCTURE_PROCESSORS.register(eventBus);
    }

}