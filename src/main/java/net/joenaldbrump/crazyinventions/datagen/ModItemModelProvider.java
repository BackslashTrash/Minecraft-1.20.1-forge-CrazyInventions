package net.joenaldbrump.crazyinventions.datagen;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CrazyInventions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.PIZZA_BASE);
        simpleItem(ModItems.TOMATO_SEEDS);
        simpleItem(ModItems.TOMATO);
        simpleItem(ModItems.CHEESE_BUCKET);
        simpleItem(ModItems.CHEESE_PIZZA_SLICE);
        simpleItem(ModItems.CHEESE_PIZZA);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CrazyInventions.MOD_ID,"item/" + item.getId().getPath()));
    }
}
