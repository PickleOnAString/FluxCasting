package net.picklestring.flux_weavers.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.items.runes.RuneItem;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.ListUtils;

import java.util.List;

public class FluxWand extends Item {
	public FluxWand(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (!world.isClient) {
			FluxWeavers.LOGGER.info("has spell?: " + stack.contains(ItemComponentRegistry.SPELL));
			if (!stack.contains(ItemComponentRegistry.SPELL)) return TypedActionResult.pass(stack);
			List<ItemStack> inv = stack.get(ItemComponentRegistry.SPELL);
			executeRunes(inv, user, world);
			return TypedActionResult.success(stack);
		}
		return TypedActionResult.pass(stack);
	}

	public void executeRunes(List<ItemStack> inventory, PlayerEntity player, World world)
	{
		if (world.isClient || player.isSneaking()) return;

		CastingContext context = new CastingContext();
		CastingContext.cast(ListUtils.listToDefaultedList(inventory, ItemStack.EMPTY), player, context);
	}
}
