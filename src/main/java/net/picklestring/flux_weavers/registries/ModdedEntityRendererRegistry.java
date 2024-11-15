package net.picklestring.flux_weavers.registries;


import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.picklestring.flux_weavers.entities.FluxArrowEntityRenderer;

public class ModdedEntityRendererRegistry {
	public static void Register() {
		EntityRendererRegistry.register(EntityRegistry.FLUX_ARROW_ENTITY, FluxArrowEntityRenderer::new);
	}
}
