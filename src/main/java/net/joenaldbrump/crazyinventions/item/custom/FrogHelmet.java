package net.joenaldbrump.crazyinventions.item.custom;

import net.joenaldbrump.crazyinventions.item.client.FryingPanArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.UUID;
import java.util.function.Consumer;

public class FrogHelmet extends ArmorItem implements GeoItem {
    public static final UUID uuid = UUID.randomUUID();
    public static final UUID uuidd = UUID.randomUUID();
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public FrogHelmet(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
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

    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return
                enchantment == Enchantments.AQUA_AFFINITY
                || super.canApplyAtEnchantingTable(stack, enchantment)
                || enchantment == Enchantments.ALL_DAMAGE_PROTECTION
                || enchantment == Enchantments.BLAST_PROTECTION
                || enchantment == Enchantments.FIRE_PROTECTION
                || enchantment == Enchantments.MENDING
                || enchantment == Enchantments.THORNS
                || enchantment == Enchantments.UNBREAKING
                || enchantment == Enchantments.PROJECTILE_PROTECTION;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
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
