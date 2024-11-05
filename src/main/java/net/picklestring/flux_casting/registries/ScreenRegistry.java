package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.RiftBenchEntity;
import net.picklestring.flux_casting.gui.RiftBenchScreen;
import net.picklestring.flux_casting.gui.RiftBenchScreenHandler;
import net.picklestring.flux_casting.gui.RuneTableScreen;
import net.picklestring.flux_casting.gui.RuneTableScreenHandler;

public class ScreenRegistry {
	public static final ScreenHandlerType<RiftBenchScreenHandler> RIFT_BENCH_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(FluxCasting.ModID, "rift_bench"), new ScreenHandlerType<>(RiftBenchScreenHandler::new, FeatureSet.empty()));
	public static final ScreenHandlerType<RuneTableScreenHandler> RUNE_TABLE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(FluxCasting.ModID, "rune_table"), new ScreenHandlerType<>(RuneTableScreenHandler::new, FeatureSet.empty()));

	public static void Register()
	{
		HandledScreens.register(RUNE_TABLE_SCREEN_HANDLER, RuneTableScreen::new);
		HandledScreens.register(RIFT_BENCH_SCREEN_HANDLER, RiftBenchScreen::new);
	}
}
