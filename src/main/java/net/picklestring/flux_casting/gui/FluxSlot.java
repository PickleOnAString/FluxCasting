package net.picklestring.flux_casting.gui;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;

public class FluxSlot extends Slot {
	public static TagKey<Item> FLUX_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier(FluxCasting.ModID, "flux_small"));
	public FluxSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return stack.isIn(FLUX_TAG);
	}
}
