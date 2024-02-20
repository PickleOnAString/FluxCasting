package net.picklestring.flux_casting.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.picklestring.flux_casting.ImplementedInventory;
import net.picklestring.flux_casting.registries.BlockEntityRegistry;
import org.jetbrains.annotations.Nullable;

public class RuneTableEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(54, ItemStack.EMPTY);

	public RuneTableEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.RUNE_TABLE_ENTITY, pos, state);
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable(getCachedState().getBlock().getTranslationKey());
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return null;
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}
}
