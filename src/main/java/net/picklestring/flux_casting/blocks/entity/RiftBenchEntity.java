package net.picklestring.flux_casting.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.recipes.RiftBenchRecipe;
import net.picklestring.flux_casting.registries.BlockEntityRegistry;
import net.picklestring.flux_casting.ImplementedInventory;
import net.picklestring.flux_casting.gui.RiftBenchScreenHandler;

import java.util.Optional;

public class RiftBenchEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
	public int infusionTime = 0;
	public boolean isInfusing = false;

	public RiftBenchEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.RIFT_BENCH_ENTITY, pos, state);
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable(getCachedState().getBlock().getTranslationKey());
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	private final PropertyDelegate isInfusingDelegate = new PropertyDelegate() {
		@Override
		public int get(int index) {
			return isInfusing ? 1 : 0;
		}

		@Override
		public void set(int index, int value) {
			isInfusing = value == 1;
		}

		//this is supposed to return the amount of integers you have in your delegate, in our example only one
		@Override
		public int size() {
			return 1;
		}
	};


	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, this.inventory);
		infusionTime = nbt.getInt("infusion_time");
        isInfusing = nbt.getBoolean("is_infusing");
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.inventory);
		nbt.putInt("infusion_time", infusionTime);
		nbt.putBoolean("is_infusing", isInfusing);
	}

	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		//We provide *this* to the screenHandler as our class Implements Inventory
		//Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
		return new RiftBenchScreenHandler(syncId, playerInventory, this, ScreenHandlerContext.create(world, pos), this.isInfusingDelegate);
	}

	public static void tick(World world, BlockPos blockPos, BlockState blockState, RiftBenchEntity riftBenchEntity) {
		if (riftBenchEntity.isInfusing) {
			riftBenchEntity.infusionTime++;
			Optional<RiftBenchRecipe> match = world.getRecipeManager().getFirstMatch(RiftBenchRecipe.RiftBenchRecipeType.INSTANCE, (RiftBenchEntity)world.getBlockEntity(blockPos), world);
			if (match.isEmpty())
			{
				riftBenchEntity.isInfusing = false;
				riftBenchEntity.infusionTime = 0;
			}

			if (riftBenchEntity.infusionTime >= 200) {
				riftBenchEntity.infusionTime = 0;
				riftBenchEntity.isInfusing = false;

				ImplementedInventory implInventory = (RiftBenchEntity)world.getBlockEntity(blockPos);
				RiftBenchRecipe recipe = match.get();
				for (int i = 0; i < implInventory.size(); i++)
				{
					ItemStack stack = implInventory.getStack(i);
					stack.setCount(stack.getCount()-1);
					implInventory.setStack(i, stack);
				}
				implInventory.setStack(0, recipe.outputStack.copy());
				((RiftBenchEntity)world.getBlockEntity(blockPos)).isInfusing = true;
			}
		}
	}
}
