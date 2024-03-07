package net.picklestring.flux_casting;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.registries.*;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FluxCasting implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String ModID = "flux_casting";
	public static final Logger LOGGER = LoggerFactory.getLogger("Flux Casting");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		InitRegistries();
	}

	public void InitRegistries() {
		ItemRegistry.Register();
		BlockRegistry.Register();
		BlockEntityRegistry.Register();
		ParticleRegistry.Register();
		ItemGroupRegistry.Register();
		ScreenRegistry.Register();
		RecipeRegistry.Register();
    }
}
