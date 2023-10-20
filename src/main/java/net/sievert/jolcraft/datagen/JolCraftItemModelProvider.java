package net.sievert.jolcraft.datagen;


import net.sievert.jolcraft.JolCraft;
import net.sievert.jolcraft.item.JolCraftItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class JolCraftItemModelProvider extends ItemModelProvider {
    public JolCraftItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JolCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(JolCraftItems.COIN);
        simpleItem(JolCraftItems.COIN_MOULD);
        spawnEgg(JolCraftItems.SPIRIT_FOX_SPAWN_EGG);
        spawnEgg(JolCraftItems.DWARF_SPAWN_EGG);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(JolCraft.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("minecraft:item/template_spawn_egg"));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(JolCraft.MOD_ID,"item/" + item.getId().getPath()));
    }
}