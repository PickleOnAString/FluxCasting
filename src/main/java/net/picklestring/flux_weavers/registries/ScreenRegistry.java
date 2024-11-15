package net.picklestring.flux_weavers.registries;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.gui.RiftBenchScreen;
import net.picklestring.flux_weavers.gui.RiftBenchScreenHandler;
import net.picklestring.flux_weavers.gui.RuneTableScreen;
import net.picklestring.flux_weavers.gui.RuneTableScreenHandler;

public class ScreenRegistry {
	public static final ScreenHandlerType<RiftBenchScreenHandler> RIFT_BENCH_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(FluxWeavers.ModID, "rift_bench"), new ScreenHandlerType<>(RiftBenchScreenHandler::new, FeatureSet.empty()));
	public static final ScreenHandlerType<RuneTableScreenHandler> RUNE_TABLE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(FluxWeavers.ModID, "rune_table"), new ScreenHandlerType<>(RuneTableScreenHandler::new, FeatureSet.empty()));

	public static void Register()
	{
		HandledScreens.register(RUNE_TABLE_SCREEN_HANDLER, RuneTableScreen::new);
		HandledScreens.register(RIFT_BENCH_SCREEN_HANDLER, RiftBenchScreen::new);
	}
}
