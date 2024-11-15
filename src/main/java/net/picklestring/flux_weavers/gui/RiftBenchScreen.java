package net.picklestring.flux_weavers.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;

public class RiftBenchScreen extends HandledScreen<RiftBenchScreenHandler> {
	//A path to the gui texture. In this example we use the texture from the dispenser
	private static final Identifier TEXTURE = Identifier.of(FluxWeavers.ModID, "textures/gui/rift_bench_screen.png");
	public static final Identifier IS_ACTIVE_TEXTURE = Identifier.of(FluxWeavers.ModID, "textures/gui/rift_bench_active_screen.png");

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
	protected void drawBackground(DrawContext graphics, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexProgram);
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
	public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
		renderBackground(graphics, mouseX, mouseY, delta);
		super.render(graphics, mouseX, mouseY, delta);
		drawMouseoverTooltip(graphics, mouseX, mouseY);
	}
}


