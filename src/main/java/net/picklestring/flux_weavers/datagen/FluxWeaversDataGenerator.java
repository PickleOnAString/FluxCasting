package net.picklestring.flux_weavers.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.registries.ItemRegistry;
import net.picklestring.flux_weavers.registries.RuneRegistry;

public class FluxWeaversDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FluxWeavers.LOGGER.info("TEST");
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(FluxWeaversModelGenerator::new);
	}

	private static class FluxWeaversModelGenerator extends FabricModelProvider {

		public FluxWeaversModelGenerator(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			RuneRegistry.GenerateItemModels(itemModelGenerator);
			ItemRegistry.GenerateItemModels(itemModelGenerator);
		}
	}
}
