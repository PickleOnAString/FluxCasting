package net.picklestring.flux_casting.items.runes;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DebugRune extends RuneItem {
	public DebugRune(Settings settings) {
		super(settings);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index) {
		executeInserters(inventory, index);
		String debugString = getDataOrDefault(0, getStringPartOrDefault(1, "", inventory.get(index)), String.class);
		FluxCasting.LOGGER.info(debugString);
		data = new ArrayList<>();
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal("Rune: Debug Rune"));
		tooltip.add(Text.literal("Data:"));
		tooltip.add(Text.literal("  > Index 0: string"));
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int index) {
		return null;
	}
}
