package net.picklestring.flux_casting.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.ImplementedInventory;
import net.picklestring.flux_casting.blocks.entity.RiftBenchEntity;
import net.picklestring.flux_casting.registries.ScreenRegistry;
import net.picklestring.flux_casting.recipes.RiftBenchRecipe;

import java.util.Optional;

public class RiftBenchScreenHandler extends ScreenHandler {
	private final ImplementedInventory implementedInventory;
	public ScreenHandlerContext context;
	public PlayerEntity player;
	public PropertyDelegate isInfusingDelegate;

	//This constructor gets called on the client when the server wants it to open the screenHandler,
	//The client will call the other constructor with an empty Inventory and the screenHandler will automatically
	//sync this empty inventory with the inventory on the server.
	public RiftBenchScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new ImplementedInventory.EMPTY(10), ScreenHandlerContext.EMPTY, new ArrayPropertyDelegate(1));
	}

	//This constructor gets called from the BlockEntity on the server without calling the other constructor first, the server knows the inventory of the container
	//and can therefore directly provide it as an argument. This inventory will then be synced to the client.
	public RiftBenchScreenHandler(int syncId, PlayerInventory playerInventory, ImplementedInventory inventory, ScreenHandlerContext context, PropertyDelegate isInfusingDelegate) {
		super(ScreenRegistry.RIFT_BENCH_SCREEN_HANDLER, syncId);
		checkSize(inventory, 10);
		this.context = context;
		this.player = playerInventory.player;
		this.implementedInventory = inventory;
		this.isInfusingDelegate = isInfusingDelegate;
		//some inventories do custom logic when a player opens it.
		inventory.onOpen(playerInventory.player);
		this.addProperties(isInfusingDelegate);

		//This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
		//This will not render the background of the slots however, this is the Screens job
		int m;
		int l;
		//Our inventory
		this.addSlot(new Slot(inventory, 0, 103, 63));
		this.addSlot(new Slot(inventory, 1, 103, 27));
		this.addSlot(new Slot(inventory, 2, 130, 36));
		this.addSlot(new Slot(inventory, 3, 139, 63));
		this.addSlot(new Slot(inventory, 4, 130, 90));
		this.addSlot(new Slot(inventory, 5, 103, 99));
		this.addSlot(new Slot(inventory, 6, 76, 90));
		this.addSlot(new Slot(inventory, 7, 67, 63));
		this.addSlot(new Slot(inventory, 8, 76, 36));
		this.addSlot(new Slot(inventory, 9, 31, 63));

		//The player inventory
		for (m = 0; m < 3; ++m) {
			for (l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 140 + m * 18));
			}
		}
		//The player Hotbar
		for (m = 0; m < 9; ++m) {
			this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 198));
		}
	}

	public RiftBenchScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
		this(syncId, playerInventory);
	}

	public boolean getIsInfusing(){
		return isInfusingDelegate.get(0) == 1;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.implementedInventory.canPlayerUse(player);
	}

	@Override
	public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
		super.onSlotClick(slotIndex, button, actionType, player);
		this.context.run((world, pos) -> craft(this, world, this.player, implementedInventory, pos));
	}

	protected static void craft(ScreenHandler handler, World world, PlayerEntity player, ImplementedInventory inventory, BlockPos pos) {
		/*FluxCasting.LOGGER.info("crafting in context");
		Optional<RiftBenchRecipe> match = world.getRecipeManager().getFirstMatch(RiftBenchRecipe.RiftBenchRecipeType.INSTANCE, inventory, world);
		if (match.isEmpty()) return;
		RiftBenchRecipe recipe = match.get();
		for (int i = 0; i < inventory.size(); i++)
		{
			FluxCasting.LOGGER.info("found recipe!");
			ItemStack stack = inventory.getStack(i);
			stack.setCount(stack.getCount()-1);
			inventory.setStack(i, stack);
		}
		inventory.setStack(0, recipe.outputStack.copy());
		((RiftBenchEntity)world.getBlockEntity(pos)).isInfusing = true;*/
		Optional<RiftBenchRecipe> match = world.getRecipeManager().getFirstMatch(RiftBenchRecipe.RiftBenchRecipeType.INSTANCE, inventory, world);
		if (match.isPresent()) ((RiftBenchEntity)world.getBlockEntity(pos)).setIsInfusing(true);
	}

	// Shift + Player Inv Slot
	@Override
	public ItemStack quickTransfer(PlayerEntity player, int invSlot) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);
		if (slot != null && slot.hasStack()) {
			ItemStack originalStack = slot.getStack();
			newStack = originalStack.copy();
			if (invSlot < this.implementedInventory.size()) {
				if (!this.insertItem(originalStack, this.implementedInventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(originalStack, 0, this.implementedInventory.size(), false)) {
				return ItemStack.EMPTY;
			}

			if (originalStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return newStack;
	}


}

