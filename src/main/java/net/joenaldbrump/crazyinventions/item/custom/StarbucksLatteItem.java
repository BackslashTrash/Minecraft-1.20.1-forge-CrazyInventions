package net.joenaldbrump.crazyinventions.item.custom;

import net.joenaldbrump.crazyinventions.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;


public class StarbucksLatteItem extends Item {
    public StarbucksLatteItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        Player $$3 = pEntityLiving instanceof Player ? (Player)pEntityLiving : null;
        if ($$3 instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)$$3, pStack);
        }

        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,1));
        }

        if ($$3 != null) {
            $$3.awardStat(Stats.ITEM_USED.get(this));
            if (!$$3.getAbilities().instabuild) {
                pStack.shrink(1);
            }
        }

        if ($$3 == null || !$$3.getAbilities().instabuild) {
            if (pStack.isEmpty()) {
                return new ItemStack(ModItems.PAPER_CUP.get());
            }

            if ($$3 != null) {
                $$3.getInventory().add(new ItemStack(ModItems.PAPER_CUP.get()));
            }
        }

        pEntityLiving.gameEvent(GameEvent.DRINK);
        return pStack;
    }
}
