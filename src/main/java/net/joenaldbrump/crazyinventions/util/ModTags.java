package net.joenaldbrump.crazyinventions.util;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {



        private static TagKey<Block>  tag(String name) {
            return BlockTags.create(new ResourceLocation(CrazyInventions.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item>CONTAINS_CHEESE = tag("contains_cheese");

        private static TagKey<Item>  tag(String name) {
            return ItemTags.create(new ResourceLocation(CrazyInventions.MOD_ID, name));
        }
    }
}
