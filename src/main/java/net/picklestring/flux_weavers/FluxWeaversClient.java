package net.picklestring.flux_weavers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.picklestring.flux_weavers.gui.hud.FluxBar;
import net.picklestring.flux_weavers.registries.*;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.SuspendParticle;

public class FluxWeaversClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FluxWeavers.LOGGER.info("client init");
		/* Registers our particle client-side.
		 * First argument is our particle's instance, created previously on ExampleMod.
		 * Second argument is the particle's factory. The factory controls how the particle behaves.
		 * In this example, we'll use FlameParticle's Factory.*/
		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FLUX_STAR, SuspendParticle.HappyVillagerFactory::new);
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.RIFT_BENCH, RenderLayer.getCutout());

		ScreenRegistry.Register();
		ItemPredicateRegistry.Register();
		ModdedEntityRendererRegistry.Register();

		HudRenderCallback.EVENT.register(new FluxBar());
	}
}
