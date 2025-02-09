package net.joenaldbrump.crazyinventions.datagen;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.block.ModBlocks;
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

        makeTomato((CropBlock) ModBlocks.TOMATO_CROP.get(),"tomato_stage","tomato_stage");
    }


    public void makeTomato(CropBlock cropBlock, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = blockState -> tomatoStatesModels(blockState, cropBlock, modelName, textureName);
        getVariantBuilder(cropBlock).forAllStates(function);
    }




    private ConfiguredModel[] tomatoStatesModels(BlockState state, CropBlock cropBlock, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoPlantBlock) cropBlock).getAgeProperty()),
                new ResourceLocation(CrazyInventions.MOD_ID,"block/" + textureName + state.getValue(((TomatoPlantBlock) cropBlock).getAgeProperty()))).renderType("cutout"));
        return models;
    }
}
