package net.joenaldbrump.crazyinventions.datagen.loot;

import net.joenaldbrump.crazyinventions.block.ModBlocks;
import net.joenaldbrump.crazyinventions.block.custom.CoffeePlantCrop;
import net.joenaldbrump.crazyinventions.block.custom.TomatoPlantBlock;
import net.joenaldbrump.crazyinventions.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import java.util.Set;


public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CHEESE_BLOCK.get());

        LootItemCondition.Builder lootcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.TOMATO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoPlantBlock.AGE, 5));

        LootItemCondition.Builder coffee$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.COFFEE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoffeePlantCrop.AGE, 5));

        LootItemCondition.Builder redpepper$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.COFFEE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CoffeePlantCrop.AGE, 5));



        this.add(ModBlocks.TOMATO_CROP.get(),
                createCrops(ModBlocks.TOMATO_CROP.get(),
                        ModItems.TOMATO.get(),
                        ModItems.TOMATO_SEEDS.get(),
                        lootcondition$builder,1,3));

        this.add(ModBlocks.COFFEE_CROP.get(),
                createCrops(ModBlocks.COFFEE_CROP.get(),
                        ModItems.RAW_COFFEE_BEANS.get(),
                        ModItems.RAW_COFFEE_BEANS.get(),
                        coffee$builder,1,1));

        this.add(ModBlocks.RED_PEPPER_CROP.get(),
                createCrops(ModBlocks.RED_PEPPER_CROP.get(),
                        ModItems.RED_PEPPER.get(),
                        ModItems.RED_PEPPER_SEEDS.get(),
                        redpepper$builder,1,3));


    }





    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createCrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropCondition, int cropLowRange, int cropHighRange) {
        return (LootTable.Builder)this.applyExplosionDecay(pCropBlock,
                LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                        .add(((LootPoolSingletonContainer.Builder)LootItem
                        .lootTableItem(pGrownCropItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(cropLowRange, cropHighRange)))
                        .when(pDropGrownCropCondition))
                        .otherwise(LootItem.lootTableItem(pSeedsItem))))
                .withPool(
                        LootPool.lootPool()
                        .when(pDropGrownCropCondition)
                        .add(LootItem.lootTableItem(pSeedsItem)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }
}
