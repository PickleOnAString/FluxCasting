package net.picklestring.flux_casting.items.runes;

import net.minecraft.item.Item;

public abstract class RuneItem extends Item {
	public RuneItem(Settings settings) {
		super(settings);
	}

	public abstract void onCast();
}
