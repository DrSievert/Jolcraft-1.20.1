package net.sievert.jolcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class JolCraftLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(JolCraftBlockLootTables::new, LootContextParamSets.BLOCK), (new LootTableProvider.SubProviderEntry(JolCraftChestLootTables::new, LootContextParamSets.CHEST))));
    }
}