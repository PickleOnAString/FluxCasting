package net.picklestring.flux_casting;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.registries.*;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FluxCasting implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String ModID = "flux_casting";
	public static final Logger LOGGER = LoggerFactory.getLogger("Flux Casting");

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
    }
}
