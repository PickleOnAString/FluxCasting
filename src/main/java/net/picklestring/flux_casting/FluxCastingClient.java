package net.picklestring.flux_casting;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.SuspendParticle;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
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
	}
}
