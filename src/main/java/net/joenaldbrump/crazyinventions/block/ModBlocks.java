package net.joenaldbrump.crazyinventions.block;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.block.custom.CoffeePlantCrop;
import net.joenaldbrump.crazyinventions.block.custom.RedPepperPlantCrop;
import net.joenaldbrump.crazyinventions.block.custom.TomatoPlantBlock;
import net.joenaldbrump.crazyinventions.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CrazyInventions.MOD_ID);

    public static final RegistryObject<Block> CHEESE_BLOCK = registerBlock("cheese_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.EMPTY)));

    public static final RegistryObject<Block> TOMATO_CROP = registerBlock("tomato_crop",
            () -> new TomatoPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.CROP).noOcclusion().noCollission()));

    public static final RegistryObject<Block> COFFEE_CROP = registerBlock("coffee_crop",
            () -> new CoffeePlantCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.CROP).noOcclusion().noCollission()));

    public static final RegistryObject<Block> RED_PEPPER_CROP = registerBlock("red_pepper_crop",
            () -> new RedPepperPlantCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.CROP).noOcclusion().noCollission()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }// helper method to register block items because blocks does not have an item that comes with it

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
