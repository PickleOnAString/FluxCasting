package net.picklestring.flux_casting.registries;

import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.InternalizedFluxComponent;

public class ComponentRegistry implements EntityComponentInitializer {
	public static final ComponentKey<InternalizedFluxComponent> INTERNALIZED_FLUX = dev.onyxstudios.cca.api.v3.component.ComponentRegistry.getOrCreate(new Identifier(FluxCasting.ModID, "internalized_flux"), InternalizedFluxComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(INTERNALIZED_FLUX, InternalizedFluxComponent::new);
	}
}
