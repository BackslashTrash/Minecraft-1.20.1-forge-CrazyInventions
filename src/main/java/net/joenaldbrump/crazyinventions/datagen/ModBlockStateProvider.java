package net.joenaldbrump.crazyinventions.datagen;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.block.ModBlocks;
import net.joenaldbrump.crazyinventions.block.custom.CoffeePlantCrop;
import net.joenaldbrump.crazyinventions.block.custom.RedPepperPlantCrop;
import net.joenaldbrump.crazyinventions.block.custom.TomatoPlantBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CrazyInventions.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWItem(ModBlocks.CHEESE_BLOCK);
    }

    private void blockWItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
        makeTomatoCrop((CropBlock) ModBlocks.TOMATO_CROP.get(),"tomato_stage","tomato_stage");
        makeCoffeeCrop((CropBlock) ModBlocks.COFFEE_CROP.get(),"coffee_stage","coffee_stage");
        makePepperCrop((CropBlock) ModBlocks.RED_PEPPER_CROP.get(),"red_pepper_stage","red_pepper_stage");
    }


    public void makeTomatoCrop(CropBlock cropBlock, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = blockState -> tomatoStatesModels(blockState, cropBlock, modelName, textureName);
        getVariantBuilder(cropBlock).forAllStates(function);
    }

    public void makeCoffeeCrop(CropBlock cropBlock, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = blockState -> coffeeStatesModels(blockState, cropBlock, modelName, textureName);
        getVariantBuilder(cropBlock).forAllStates(function);
    }

    public void makePepperCrop(CropBlock cropBlock, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = blockState -> redPepperStatesModels(blockState, cropBlock, modelName, textureName);
        getVariantBuilder(cropBlock).forAllStates(function);
    }




    private ConfiguredModel[] tomatoStatesModels(BlockState state, CropBlock cropBlock, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoPlantBlock) cropBlock).getAgeProperty()),
                new ResourceLocation(CrazyInventions.MOD_ID,"block/" + textureName + state.getValue(((TomatoPlantBlock) cropBlock).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    private ConfiguredModel[] coffeeStatesModels(BlockState state, CropBlock cropBlock, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CoffeePlantCrop) cropBlock).getAgeProperty()),
                new ResourceLocation(CrazyInventions.MOD_ID,"block/" + textureName + state.getValue(((CoffeePlantCrop) cropBlock).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    private ConfiguredModel[] redPepperStatesModels(BlockState state, CropBlock cropBlock, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RedPepperPlantCrop) cropBlock).getAgeProperty()),
                new ResourceLocation(CrazyInventions.MOD_ID,"block/" + textureName + state.getValue(((RedPepperPlantCrop) cropBlock).getAgeProperty()))).renderType("cutout"));
        return models;
    }
}
