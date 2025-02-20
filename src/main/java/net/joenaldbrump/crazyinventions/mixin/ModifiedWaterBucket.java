package net.joenaldbrump.crazyinventions.mixin;


import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BucketItem.class)
public class ModifiedWaterBucket {

    @Unique
    @Mutable
    private static final Item.Properties forge1_20_1$asd = new Item.Properties();

    @Unique
    @Mutable
    private static final Item.Properties forge1_20_1$slatte = new Item.Properties();

    @Inject(method = "<init>(Ljava/util/function/Supplier;Lnet/minecraft/world/item/Item$Properties;)V", at = @At("RETURN"))
    private void modifyWaterBucket(CallbackInfo callbackInfo){
        if ((BucketItem)(Object)this == Items.WATER_BUCKET) {
            forge1_20_1$asd.craftRemainder(Items.BUCKET);
        }
        if ((Object)this == Items.MILK_BUCKET) {
            forge1_20_1$slatte.craftRemainder(Items.BUCKET);
        }
    }
}
