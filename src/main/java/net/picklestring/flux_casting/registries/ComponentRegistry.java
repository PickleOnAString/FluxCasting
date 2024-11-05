package net.picklestring.flux_casting.registries;

import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.InternalizedFluxComponent;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ComponentRegistry implements EntityComponentInitializer {
	public static final ComponentKey<InternalizedFluxComponent> INTERNALIZED_FLUX = org.ladysnake.cca.api.v3.component.ComponentRegistry.getOrCreate(Identifier.of(FluxCasting.ModID, "internalized_flux"), InternalizedFluxComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(INTERNALIZED_FLUX, InternalizedFluxComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
	}
}
