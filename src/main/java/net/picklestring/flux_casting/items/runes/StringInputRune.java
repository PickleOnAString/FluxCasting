package net.picklestring.flux_casting.items.runes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.picklestring.flux_casting.FluxCasting;

public class StringInputRune extends RuneItem {

	public StringInputRune(Settings settings) {
		super(settings);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int index) {
		FluxCasting.LOGGER.info("StringInputRune: getValue");
		return inventory.get(index).getName().getString();
	}
}
