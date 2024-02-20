package net.picklestring.flux_casting.items.runes;

import net.picklestring.flux_casting.FluxCasting;

public class DebugRune extends RuneItem {
	public DebugRune(Settings settings) {
		super(settings);
	}

	@Override
	public void onCast() {
		FluxCasting.LOGGER.info("Test");
	}
}
