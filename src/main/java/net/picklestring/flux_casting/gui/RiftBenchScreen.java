package net.picklestring.flux_casting.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;

public class RiftBenchScreen extends HandledScreen<RiftBenchScreenHandler> {
	//A path to the gui texture. In this example we use the texture from the dispenser
	private static final Identifier TEXTURE = new Identifier(FluxCasting.ModID, "textures/gui/rift_bench_screen.png");
	public static final Identifier IS_ACTIVE_TEXTURE = new Identifier(FluxCasting.ModID, "textures/gui/rift_bench_active_screen.png");

	public RiftBenchScreen(RiftBenchScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
		this.backgroundHeight = 114 + 6 * 18;
		this.playerInventoryTitleY = 128;
	}

	@Override
	protected void init() {
		super.init();
		// Center the title
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
	}

	@Override
	protected void drawBackground(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
        if (handler.getIsInfusing()) {
            graphics.drawTexture(IS_ACTIVE_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        } else {
            graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        }
    }

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
		renderBackground(graphics);
		super.render(graphics, mouseX, mouseY, delta);
		drawMouseoverTooltip(graphics, mouseX, mouseY);
	}
}


