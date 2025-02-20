package net.joenaldbrump.crazyinventions.item;

import net.joenaldbrump.crazyinventions.block.ModBlocks;
import net.joenaldbrump.crazyinventions.item.custom.CheeseBucketItem;
import net.joenaldbrump.crazyinventions.item.custom.PanHelmet;
import net.joenaldbrump.crazyinventions.item.custom.StarbucksLatteItem;
import net.minecraft.world.food.FoodProperties;
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

    public static final RegistryObject<Item> RAW_COFFEE_BEANS = ITEMS.register("raw_coffee_beans",
            () -> new ItemNameBlockItem(ModBlocks.COFFEE_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> ROASTED_COFFEE_BEANS = ITEMS.register("roasted_coffee_beans",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAPER_CUP = ITEMS.register("paper_cup",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARBUCKS_LATTE = ITEMS.register("starbucks_latte",
            () -> new StarbucksLatteItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));

    public static final RegistryObject<Item> FRYING_PAN = ITEMS.register("frying_pan",
            () -> new PanHelmet(ModArmorMaterials.PAN, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PEPPERONI_SAUSAGE = ITEMS.register("raw_pepperoni_sausage",
            () -> new Item(new Item.Properties().food(ModFoodItems.PEPPERONI_SAUSAGE)));

    public static final RegistryObject<Item> COOKED_PEPPERONI_SAUSAGE = ITEMS.register("cooked_pepperoni_sausage",
            () -> new Item(new Item.Properties().food(ModFoodItems.COOKED_PEPPERONI_SAUSAGE)));

    public static final RegistryObject<Item> RED_PEPPER_SEEDS = ITEMS.register("red_pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RED_PEPPER_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> RED_PEPPER = ITEMS.register("red_pepper",
            () -> new ItemNameBlockItem(ModBlocks.RED_PEPPER_CROP.get(), new Item.Properties().food(ModFoodItems.TOMATO)));

    public static final RegistryObject<Item> PAPRIKA = ITEMS.register("paprika",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
