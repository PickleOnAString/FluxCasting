package net.picklestring.flux_weavers.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;

import java.util.List;

public class BottleOScarredStone extends Item {
	public BottleOScarredStone(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int count = stack.getComponents().get(ItemComponentRegistry.FLUX)/20;
		tooltip.add(Text.translatable("item.flux_weavers.bottle_o_scarred_stone.tooltip", count));
	}
}
