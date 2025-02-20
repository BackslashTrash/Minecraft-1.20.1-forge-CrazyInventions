package net.joenaldbrump.crazyinventions.item;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    PAN("frying_pan", 15, new int[]{2,5,6,2},25, SoundEvents.BELL_BLOCK,1f,0f, ()-> Ingredient.of(ModItems.CHEESE.get()));
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float kbResistance;
    private final Supplier<Ingredient> ingredientSupplier;
    private static final int[] BASE_DURABILITY = {2,5,6,2};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent soundEvent, float toughness, float kbResistance, Supplier<Ingredient> ingredientSupplier){

        this.durabilityMultiplier=durabilityMultiplier;
        this.enchantmentValue = enchantmentValue;
        this.kbResistance = kbResistance;
        this.name = name;
        this.toughness = toughness;
        this.protectionAmounts = protectionAmounts;
        this.soundEvent =soundEvent;
        this.ingredientSupplier = ingredientSupplier;

    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.ingredientSupplier.get();
    }

    @Override
    public String getName() {
        return CrazyInventions.MOD_ID+":"+this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.kbResistance;
    }
}
