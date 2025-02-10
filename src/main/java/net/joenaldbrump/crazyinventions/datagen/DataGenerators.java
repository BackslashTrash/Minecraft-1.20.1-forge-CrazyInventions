package net.joenaldbrump.crazyinventions.datagen;

import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CrazyInventions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();
        gen.addProvider(e.includeServer(), new ModRecipeProvider(packOutput));
        gen.addProvider(e.includeServer(), ModLootTableProvider.create(packOutput));

        gen.addProvider(e.includeClient(), new ModBlockStateProvider(packOutput,existingFileHelper));
        gen.addProvider(e.includeClient(), new ModItemModelProvider(packOutput,existingFileHelper));

        ModBlockTagGenerator blockTagProvider = gen.addProvider(e.includeServer(), new ModBlockTagGenerator(packOutput, lookupProvider,existingFileHelper));
        gen.addProvider(e.includeClient(), new ModItemTagGenerator(packOutput, lookupProvider,blockTagProvider.contentsGetter(),existingFileHelper));


    }


}
