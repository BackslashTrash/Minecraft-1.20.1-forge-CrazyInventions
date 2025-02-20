package net.joenaldbrump.crazyinventions.item;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab>
            CREATIVE_TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CrazyInventions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CTAB = CREATIVE_TAB_REGISTER.register("crazyinventions_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.crazyinventions_tab"))
            .icon(() -> new ItemStack(ModItems.CHEESE.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.CHEESE.get());
                output.accept(ModItems.PIZZA_BASE.get());
                output.accept(ModBlocks.CHEESE_BLOCK.get());
                output.accept(ModItems.TOMATO_SEEDS.get());
                output.accept(ModItems.TOMATO.get());
                output.accept(ModItems.CHEESE_PIZZA.get());
                output.accept(ModItems.CHEESE_PIZZA_SLICE.get());
                output.accept(ModItems.CHEESE_BUCKET.get());
                output.accept(ModItems.RAW_COFFEE_BEANS.get());
                output.accept(ModItems.ROASTED_COFFEE_BEANS.get());
                output.accept(ModItems.PAPER_CUP.get());
                output.accept(ModItems.FRYING_PAN.get());
                output.accept(ModItems.PEPPERONI_SAUSAGE.get());
                output.accept(ModItems.COOKED_PEPPERONI_SAUSAGE.get());
                output.accept(ModItems.RED_PEPPER_SEEDS.get());
                output.accept(ModItems.RED_PEPPER.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_TAB_REGISTER.register(eventBus);
    }
}
