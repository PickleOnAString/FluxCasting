package net.picklestring.flux_weavers.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;

@Environment(EnvType.CLIENT)
public class FluxArrowEntityRenderer extends ProjectileEntityRenderer<FluxArrowEntity> {
	public static final Identifier TEXTURE = Identifier.of(FluxWeavers.ModID, "textures/entity/projectiles/flux_arrow.png");

	public FluxArrowEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	public Identifier getTexture(FluxArrowEntity fluxArrowEntity) {
		return TEXTURE;
	}
}
