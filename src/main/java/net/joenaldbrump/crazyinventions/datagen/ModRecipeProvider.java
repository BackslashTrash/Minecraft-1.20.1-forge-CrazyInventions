package net.joenaldbrump.crazyinventions.datagen;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.block.ModBlocks;
import net.joenaldbrump.crazyinventions.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final List<ItemLike> CHEESE_PIZZA = List.of(ModItems.CHEESE_PIZZA.get());
    public static final List<ItemLike> CHEESE_PIZZA_SLICE =  List.of(ModItems.CHEESE_PIZZA_SLICE.get());
    public static final List<ItemLike> MILK_BUCKET = List.of(Items.MILK_BUCKET);
    public static final List<ItemLike> RAW_COFFEE_BEANS = List.of(ModItems.RAW_COFFEE_BEANS.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {


        nineBlockStorageRecipes(consumer, RecipeCategory.FOOD, ModItems.CHEESE.get(), RecipeCategory.FOOD, ModBlocks.CHEESE_BLOCK.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.CHEESE_PIZZA_SLICE.get(),8)
                .requires(ModItems.CHEESE_PIZZA.get())
                .unlockedBy(getHasName(ModItems.CHEESE_PIZZA.get()),has(ModItems.CHEESE_PIZZA.get()))
                .save(consumer);

        oreSmelting(consumer, MILK_BUCKET, RecipeCategory.FOOD, ModItems.CHEESE_BUCKET.get(), 0.25f, 100, "cheese_bucket");
        oreSmelting(consumer, RAW_COFFEE_BEANS, RecipeCategory.FOOD, ModItems.ROASTED_COFFEE_BEANS.get(), 0.25f, 100, "roasted_coffee_beans");


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHEESE_PIZZA.get(),1)
                .pattern("   ")
                .pattern("ccc")
                .pattern("tpt")
                .define('c', ModItems.CHEESE.get())
                .define('t', ModItems.TOMATO.get())
                .define('p', ModItems.PIZZA_BASE.get())
                .unlockedBy(getHasName(ModItems.PIZZA_BASE.get()),has(ModItems.PIZZA_BASE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE.get(),9)
                .requires(ModItems.CHEESE_BUCKET.get())
                .unlockedBy(getHasName(ModItems.CHEESE_BUCKET.get()),has(ModItems.CHEESE_BUCKET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.PIZZA_BASE.get(),1)
                .pattern(" w ")
                .pattern("wcw")
                .pattern(" w ")
                .define('w', Items.WHEAT)
                .define('c', Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.WHEAT),has(Items.WHEAT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STARBUCKS_LATTE.get(),1)
                .requires(Items.WATER_BUCKET)
                .requires(Items.MILK_BUCKET)
                .requires(ModItems.PAPER_CUP.get())
                .requires(Items.SUGAR)
                .requires(ModItems.ROASTED_COFFEE_BEANS.get())
                .unlockedBy(getHasName(ModItems.ROASTED_COFFEE_BEANS.get()),has(ModItems.ROASTED_COFFEE_BEANS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PAPER_CUP.get(), 2)
                .pattern("   ")
                .pattern("cc ")
                .pattern("cc ")
                .define('c',Items.PAPER)
                .unlockedBy(getHasName(Items.PAPER),has(Items.PAPER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FRYING_PAN.get(),1)
                .pattern("   ")
                .pattern("hhm")
                .pattern("hhh")
                .define('h', Items.IRON_INGOT)
                .define('m', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);




    }



    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory,
                    pResult,
                    pExperience,
                    pCookingTime,
                    pCookingSerializer)
                    .group(pGroup)
                    .unlockedBy(
                            getHasName(itemlike),
                            has(itemlike))
                    .save(pFinishedRecipeConsumer, CrazyInventions.MOD_ID + ":" +
                            getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @javax.annotation.Nullable String pPackedGroup, String pUnpackedName, @javax.annotation.Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9).requires(pPacked).group(pUnpackedGroup).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked)
                .define('#', pUnpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###").group(pPackedGroup).unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer, new ResourceLocation(pPackedName));
    }
}
