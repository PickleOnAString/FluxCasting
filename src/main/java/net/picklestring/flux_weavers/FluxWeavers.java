package net.picklestring.flux_weavers;

import net.fabricmc.api.ModInitializer;
import net.picklestring.flux_weavers.recipes.RiftBenchRecipe;
import net.picklestring.flux_weavers.registries.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FluxWeavers implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String ModID = "flux_weavers";
	public static final Logger LOGGER = LoggerFactory.getLogger("Flux Weavers");

	@Override
	public void onInitialize() {
		InitRegistries();
	}

	public void InitRegistries() {
		ItemRegistry.Register();
		RuneRegistry.Register();
		BlockRegistry.Register();
		BlockEntityRegistry.Register();
		ParticleRegistry.Register();
		ItemGroupRegistry.Register();
		RecipeRegistry.Register();
		ItemComponentRegistry.Register();
		EntityRegistry.Register();
    }
}
