package net.picklestring.flux_casting.items.runes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.picklestring.flux_casting.FluxCasting;

public class DebugRune extends RuneItem {
	public DebugRune(Settings settings) {
		super(settings);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index) {
		executeInserters(inventory, index);
		String debugString = getDataOrDefault(0, "", String.class);
		FluxCasting.LOGGER.info(debugString);
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int index) {
		return null;
	}
}
