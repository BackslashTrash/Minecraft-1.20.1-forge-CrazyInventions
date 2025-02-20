package net.joenaldbrump.crazyinventions.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.joenaldbrump.crazyinventions.item.client.FryingPanArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.UUID;
import java.util.function.Consumer;

public class PanHelmet extends ArmorItem implements GeoItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public static final UUID uuid = UUID.randomUUID();
    public static final UUID uuidd = UUID.randomUUID();
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public PanHelmet(ArmorMaterial pMaterial, Properties pProperties) {
        super(pMaterial, Type.HELMET, pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"Attack damage", 6, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuidd,"Attack speed", -2.4, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND){
            return this.defaultModifiers;
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.SHARPNESS
                || enchantment == Enchantments.AQUA_AFFINITY
                || super.canApplyAtEnchantingTable(stack, enchantment)
                || enchantment == Enchantments.ALL_DAMAGE_PROTECTION
                || enchantment == Enchantments.BLAST_PROTECTION
                || enchantment == Enchantments.FIRE_ASPECT
                || enchantment == Enchantments.FIRE_PROTECTION
                || enchantment == Enchantments.MENDING
                || enchantment == Enchantments.SILK_TOUCH
                || enchantment == Enchantments.THORNS
                || enchantment == Enchantments.UNBREAKING
                || enchantment == Enchantments.PROJECTILE_PROTECTION
                || enchantment == Enchantments.MOB_LOOTING
                || enchantment == Enchantments.KNOCKBACK;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",0,this::predicate));

    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private FryingPanArmorRenderer panArmorRenderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.panArmorRenderer == null)
                    this.panArmorRenderer = new FryingPanArmorRenderer();
                this.panArmorRenderer.prepForRender(livingEntity,itemStack,equipmentSlot,original);
                return this.panArmorRenderer;
            }
        });
    }



}
