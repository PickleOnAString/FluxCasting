package net.picklestring.flux_casting.items;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;
import net.picklestring.flux_casting.items.runes.RuneItem;

import java.util.ArrayList;

public class FluxWand extends Item {
	public FluxWand(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (context.getPlayer().isSneaking())
		{
			BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
			if (!(blockEntity instanceof RuneTableEntity runeTable)) return ActionResult.PASS;

            NbtCompound nbt;
			if (context.getStack().hasNbt()) {
				nbt = context.getStack().getNbt();
			} else {
				nbt = new NbtCompound();
			}
			Inventories.writeNbt(nbt, runeTable.getItems());
			context.getPlayer().getStackInHand(context.getHand()).setNbt(nbt);

			context.getPlayer().sendMessage(Text.translatable("item.flux_casting.flux_wand.spell_bound").formatted(Formatting.AQUA), true);

			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (world.isClient) return TypedActionResult.pass(stack);
		if (!stack.hasNbt()) return TypedActionResult.pass(stack);
		DefaultedList<ItemStack> inv = DefaultedList.ofSize(60, ItemStack.EMPTY);
		Inventories.readNbt(stack.getNbt(), inv);
		executeRunes(inv, user, world);
		return TypedActionResult.success(stack);
	}

	public void executeRunes(DefaultedList<ItemStack> inventory, PlayerEntity player, World world)
	{
		if (world.isClient) return;

		for(int i = 0; i < inventory.size(); i++)
		{
			ItemStack stack = inventory.get(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem)
				{
					((RuneItem)stack.getItem()).onCast(inventory, i, player, new Vec3d(player.getX(), player.getY(), player.getZ()), world);
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
