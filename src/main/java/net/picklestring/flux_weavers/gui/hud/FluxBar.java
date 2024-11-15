package net.picklestring.flux_weavers.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.InternalizedFluxComponent;
import net.picklestring.flux_weavers.registries.ComponentRegistry;
import net.picklestring.flux_weavers.registries.ItemRegistry;

public class FluxBar implements HudRenderCallback {
	public static Identifier FLUX_BAR_BACKGROUND_TEXTURE = Identifier.of(FluxWeavers.ModID, "textures/gui/hud/flux_bar_background.png");
	public static Identifier FLUX_BAR_FILL_TEXTURE = Identifier.of(FluxWeavers.ModID, "textures/gui/hud/flux_bar_fill.png");

	@Override
	public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
		int x = 0;
		int y = 0;
		int fillPercent = 0;
		if (MinecraftClient.getInstance() != null)
		{
			if (!MinecraftClient.getInstance().player.isHolding(ItemRegistry.FLUX_WAND) && !MinecraftClient.getInstance().player.isHolding(ItemRegistry.FLUX_BOW)) return;

			InternalizedFluxComponent component = ComponentRegistry.INTERNALIZED_FLUX.get(MinecraftClient.getInstance().player);
			float fillAmount = component.getValue();
			float maxFill = component.getMaxValue();

			fillPercent = Math.round((fillAmount / maxFill)*106f);

			x = MinecraftClient.getInstance().getWindow().getScaledWidth();
            y = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
		}

		drawContext.drawTexture(FLUX_BAR_BACKGROUND_TEXTURE, x-36, y-61, 0, 0, 32, 122);
		drawContext.drawTexture(FLUX_BAR_FILL_TEXTURE, x-28, y+53-fillPercent, 0, 0, 16, fillPercent);
	}
}
