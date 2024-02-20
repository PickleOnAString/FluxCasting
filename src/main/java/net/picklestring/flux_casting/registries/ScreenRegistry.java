package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.RiftBenchEntity;
import net.picklestring.flux_casting.gui.RiftBenchScreen;
import net.picklestring.flux_casting.gui.RiftBenchScreenHandler;
import net.picklestring.flux_casting.gui.RuneTableScreenHandler;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class ScreenRegistry {
	public static final ScreenHandlerType<RiftBenchScreenHandler> RIFT_BENCH_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(FluxCasting.ModID, "rift_bench"), RiftBenchScreenHandler::new);
	public static final ScreenHandlerType<RuneTableScreenHandler> RUNE_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(FluxCasting.ModID, "rune_table"), RuneTableScreenHandler::new);

	public static void Register()
	{
		//Registry.register(Registries.SCREEN_HANDLER_TYPE, new Identifier(FluxCasting.ModID, "rift_bench"), RIFT_BENCH_SCREEN_HANDLER);
	}
}
