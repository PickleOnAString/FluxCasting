package net.picklestring.flux_casting;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleRegistry {

	public static final DefaultParticleType FLUX_STAR = FabricParticleTypes.simple();

	public static void Register()
	{
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(FluxCasting.ModID, "flux_star"), FLUX_STAR);
	}
}
