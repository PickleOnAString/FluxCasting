package net.picklestring.flux_casting.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.ImplementedInventory;
import net.picklestring.flux_casting.gui.RuneTableScreenHandler;
import net.picklestring.flux_casting.items.runes.RuneItem;
import net.picklestring.flux_casting.registries.BlockEntityRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class RuneTableEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(60, ItemStack.EMPTY);

	public RuneTableEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.RUNE_TABLE_ENTITY, pos, state);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, this.inventory);
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.inventory);
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable(getCachedState().getBlock().getTranslationKey());
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new RuneTableScreenHandler(syncId, playerInventory, this, ScreenHandlerContext.create(world, pos));
	}

	public void executeRunes()
	{
		for(int i = 0; i < inventory.size(); i++)
		{
			ItemStack stack = inventory.get(i);
            if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem)
				{
					((RuneItem)stack.getItem()).onCast(inventory, i, null, new Vec3d(pos.getX(), pos.getY(), pos.getZ()), world);
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

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}
}
