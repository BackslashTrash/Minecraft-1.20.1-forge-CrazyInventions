package net.joenaldbrump.crazyinventions.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodItems {
    public static final FoodProperties TOMATO = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.6f)
            .build();

    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.3f)
            .build();

    public static final FoodProperties CHEESE_PIZZA =  new FoodProperties.Builder()
            .nutrition(32)
            .saturationMod(12f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED,1280), 1f)
            .build();

    public static final FoodProperties CHEESE_PIZZA_SLICE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(1.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED,160), 1f)
            .build();

    public static final FoodProperties PEPPERONI_SAUSAGE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(2f)
            .build();

    public static final FoodProperties COOKED_PEPPERONI_SAUSAGE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(8f)
            .build();
}