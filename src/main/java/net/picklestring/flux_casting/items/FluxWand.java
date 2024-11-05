package net.picklestring.flux_casting.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.runes.RuneItem;
import net.picklestring.flux_casting.registries.ItemComponentRegistry;
import net.picklestring.flux_casting.utils.CastingContext;
import net.picklestring.flux_casting.utils.ListUtils;

import java.util.List;

public class FluxWand extends Item {
	public FluxWand(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		FluxCasting.LOGGER.info("has spell?: "+stack.contains(ItemComponentRegistry.SPELL));
		if (world.isClient) return TypedActionResult.pass(stack);
		if (!stack.contains(ItemComponentRegistry.SPELL)) return TypedActionResult.pass(stack);
		List<ItemStack> inv = stack.get(ItemComponentRegistry.SPELL);
		executeRunes(inv, user, world);
		return TypedActionResult.success(stack);
	}

	public void executeRunes(List<ItemStack> inventory, PlayerEntity player, World world)
	{
		if (world.isClient || player.isSneaking()) return;

		CastingContext context = new CastingContext();

		for(int i = 0; i < inventory.size(); i++)
		{
			ItemStack stack = inventory.get(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem)
				{
					((RuneItem)stack.getItem()).onCast(ListUtils.listToDefaultedList(inventory, ItemStack.EMPTY), i, player, new Vec3d(player.getX(), player.getY(), player.getZ()), world, context);
				}
			}
		}
		for (ItemStack stack : inventory) {
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem rune) {
					rune.data = new Object[rune.dataFormat.length];
				}
			}
		}
	}
}
