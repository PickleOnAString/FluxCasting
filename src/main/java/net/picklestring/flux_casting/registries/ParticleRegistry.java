package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;

public class ParticleRegistry {

	public static final SimpleParticleType FLUX_STAR = FabricParticleTypes.simple();

	public static void Register()
	{
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(FluxCasting.ModID, "flux_star"), FLUX_STAR);
	}
}
