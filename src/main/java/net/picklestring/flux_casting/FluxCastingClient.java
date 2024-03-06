package net.picklestring.flux_casting;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.picklestring.flux_casting.gui.RiftBenchScreen;
import net.picklestring.flux_casting.gui.RuneTableScreen;
import net.picklestring.flux_casting.gui.hud.FluxBar;
import net.picklestring.flux_casting.registries.BlockRegistry;
import net.picklestring.flux_casting.registries.ParticleRegistry;
import net.picklestring.flux_casting.registries.ScreenRegistry;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.SuspendParticle;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class FluxCastingClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		FluxCasting.LOGGER.info("client init");
		/* Registers our particle client-side.
		 * First argument is our particle's instance, created previously on ExampleMod.
		 * Second argument is the particle's factory. The factory controls how the particle behaves.
		 * In this example, we'll use FlameParticle's Factory.*/
		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FLUX_STAR, SuspendParticle.HappyVillagerFactory::new);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), BlockRegistry.RIFT_BENCH);

		HandledScreens.register(ScreenRegistry.RIFT_BENCH_SCREEN_HANDLER, RiftBenchScreen::new);
		HandledScreens.register(ScreenRegistry.RUNE_TABLE_SCREEN_HANDLER, RuneTableScreen::new);

		HudRenderCallback.EVENT.register(new FluxBar());
	}
}
