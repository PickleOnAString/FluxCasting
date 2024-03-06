package net.picklestring.flux_casting.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.registries.ItemRegistry;

public class FluxBar implements HudRenderCallback {
	public static Identifier FLUX_BAR_BACKGROUND_TEXTURE = new Identifier(FluxCasting.ModID, "textures/gui/hud/flux_bar_background.png");
	public static Identifier FLUX_BAR_FILL_TEXTURE = new Identifier(FluxCasting.ModID, "textures/gui/hud/flux_bar_fill.png");

	@Override
	public void onHudRender(GuiGraphics drawContext, float tickDelta) {
		int x = 0;
		int y = 0;
		if (MinecraftClient.getInstance() != null)
		{
			if (!MinecraftClient.getInstance().player.isHolding(ItemRegistry.FLUX_WAND)) return;
			x = MinecraftClient.getInstance().getWindow().getScaledWidth();
            y = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
		}

		drawContext.drawTexture(FLUX_BAR_BACKGROUND_TEXTURE, x-36, y-61, 0, 0, 32, 122);
		drawContext.drawTexture(FLUX_BAR_FILL_TEXTURE, x-28, y-53, 0, 0, 16, 106);
	}
}
