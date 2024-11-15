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

		for (int i = 0; i < inventory.size(); i++) {
			ItemStack stack = inventory.get(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem) {
					if (stack.getItem() instanceof RuneItem) {
						if (context.isStack) {
							Object[] stackObjects = ((RuneItem) stack.getItem()).getValue(ListUtils.listToDefaultedList(inventory, ItemStack.EMPTY), i, player, new Vec3d(player.getX(), player.getY(), player.getZ()), world, context);
							if (stackObjects != null) {
								for (Object stackObject : stackObjects) {
									context.stack.push(stackObject);
								}
							}
							if (context.isCanceled) return;
						}
						FluxWeavers.LOGGER.info("Stack" + String.valueOf(context.stack));
					}
					((RuneItem) stack.getItem()).onCast(ListUtils.listToDefaultedList(inventory, ItemStack.EMPTY), i, player, new Vec3d(player.getX(), player.getY(), player.getZ()), world, context);
				}
			}
		}
		for (ItemStack stack : inventory) {
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem rune) {
					if (rune.dataFormat == null) {
						rune.data = new Object[0];
					}
					else {
						rune.data = new Object[rune.dataFormat.length];
					}
				}
			}
		}
	}
}
