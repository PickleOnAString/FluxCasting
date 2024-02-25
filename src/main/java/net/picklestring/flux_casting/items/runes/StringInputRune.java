package net.picklestring.flux_casting.items.runes;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StringInputRune extends RuneItem {

	public StringInputRune(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal("Rune: String Input Rune"));
		tooltip.add(Text.literal("Data:"));
		tooltip.add(Text.literal("  > Index 0: string"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int index) {
		FluxCasting.LOGGER.info("StringInputRune: getValue");
		return getStringPartOrDefault(1, "", inventory.get(index));
	}
}
