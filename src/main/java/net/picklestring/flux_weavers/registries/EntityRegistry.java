package net.picklestring.flux_weavers.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.blocks.RuneTable;
import net.picklestring.flux_weavers.entities.FluxArrowEntity;

public class EntityRegistry {
	public static final EntityType<FluxArrowEntity> FLUX_ARROW_ENTITY = Registry.register(
		Registries.ENTITY_TYPE,
		Identifier.of(FluxWeavers.ModID, "flux_arrow"),
		EntityType.Builder.<FluxArrowEntity>create(FluxArrowEntity::new, SpawnGroup.MISC)
			.dimensions(0.5F, 0.5F)
			.eyeHeight(0.13F)
			.maxTrackingRange(4)
			.trackingTickInterval(20)
			.build("flux_arrow")
	);

	public static void Register() {

	}
}
