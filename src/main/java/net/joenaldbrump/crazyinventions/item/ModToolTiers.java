package net.joenaldbrump.crazyinventions.item;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier CHEESE = TierSortingRegistry.registerTier(
            new ForgeTier(2,400,4f,2f,25, ModTags.Blocks.REQUIRES_CHEESE_TOOLS,
                    () -> Ingredient.of(ModItems.CHEESE.get())),
            new ResourceLocation(CrazyInventions.MOD_ID,"cheese"), List.of(Tiers.DIAMOND),List.of()
    );
}
