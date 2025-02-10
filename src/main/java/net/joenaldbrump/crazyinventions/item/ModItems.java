package net.joenaldbrump.crazyinventions.item;

import net.joenaldbrump.crazyinventions.block.ModBlocks;
import net.joenaldbrump.crazyinventions.item.custom.CheeseBucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.joenaldbrump.crazyinventions.CrazyInventions.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(ModFoodItems.CHEESE)));

    public static final RegistryObject<Item> PIZZA_BASE = ITEMS.register("pizza_base",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> TOMATO= ITEMS.register("tomato",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties().food(ModFoodItems.TOMATO)));

    public static final RegistryObject<Item> CHEESE_PIZZA = ITEMS.register("cheese_pizza",
            () -> new Item(new Item.Properties().food(ModFoodItems.CHEESE_PIZZA).stacksTo(1)));

    public static final RegistryObject<Item> CHEESE_PIZZA_SLICE = ITEMS.register("cheese_pizza_slice",
            () -> new Item(new Item.Properties().food(ModFoodItems.CHEESE_PIZZA_SLICE)));

    public static final RegistryObject<Item> CHEESE_BUCKET = ITEMS.register("cheese_bucket",
            () -> new CheeseBucketItem(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(16)));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
