package net.picklestring.flux_casting.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BottleOScarredStone extends Item {
	public BottleOScarredStone(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getNbt() == null)
		{
			tooltip.add(Text.translatable("item.flux_casting.bottle_o_scarred_stone.tooltip", 0));
			return;
		}

        int count = stack.getNbt().contains("infused_flux") ? stack.getNbt().getInt("infused_flux")/20 : 0;
		tooltip.add(Text.translatable("item.flux_casting.bottle_o_scarred_stone.tooltip", count));
	}
}
