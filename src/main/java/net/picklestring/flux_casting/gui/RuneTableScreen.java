package net.picklestring.flux_casting.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.runes.RuneItem;

public class RuneTableScreen extends HandledScreen<RuneTableScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(FluxCasting.ModID, "textures/gui/rune_table_screen.png");
	private static final Identifier TEXTURE_FLUX_INPUT = new Identifier(FluxCasting.ModID, "textures/gui/rune_table_screen_flux_input.png");

	private static final Identifier RUNE_OVERLAY = new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/debug_rune_overlay.png");

	public RuneTableScreen(RuneTableScreenHandler handler, PlayerInventory inventory, Text title) {
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
		graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
		graphics.drawTexture(TEXTURE_FLUX_INPUT, x+180, y+10, 0, 0, 32, 123);

		for (int m = 0; m < 6; m++)
		{
			if (handler.implementedInventory.getStack(54+m).isEmpty()) {
				graphics.drawTexture(TEXTURE, x + 188, y + 18 + (m * 18), 0, 222, 16, 16);
			}
		}
	}

	protected void drawOverlay(GuiGraphics graphics, int mouseX, int mouseY)
	{
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		if (!hasControlDown()) return;
		RenderSystem.enableBlend();
		for (Slot slot : handler.slots)
		{
			if (!slot.hasStack() || !(slot.getStack().getItem() instanceof RuneItem rune)) continue;
			graphics.drawTexture(rune.OVERLAY_TEXTURE, slot.x+x, slot.y+y, 350, 0f, 0f, 16, 16, 16, 16);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
		renderBackground(graphics);
		super.render(graphics, mouseX, mouseY, delta);
		drawOverlay(graphics, mouseX, mouseY);
		drawMouseoverTooltip(graphics, mouseX, mouseY);
	}

	@Override
	protected boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int button) {
		//return mouseX < (double)left || mouseY < (double)top || mouseX >= (double)(left + this.backgroundWidth) || mouseY >= (double)(top + this.backgroundHeight);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		boolean isInsideMainTexture = (mouseY >= y && mouseY <= y+backgroundHeight) && (mouseX >= x && mouseX <= x+backgroundWidth);
		boolean isInsideFluxInput = (mouseY >= y+10 && mouseY <= y+10+123) && (mouseX >= x+180 && mouseX <= x+180+32);
		return !(isInsideMainTexture || isInsideFluxInput);
	}
}
