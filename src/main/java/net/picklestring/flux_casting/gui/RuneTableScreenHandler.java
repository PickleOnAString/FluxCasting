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
import net.picklestring.flux_casting.ImplementedInventory;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;
import net.picklestring.flux_casting.registries.ScreenRegistry;

public class RuneTableScreenHandler extends ScreenHandler {
	public ScreenHandlerContext context;
	public PlayerEntity player;
	public ImplementedInventory implementedInventory;

	public RuneTableScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		this(syncId, playerInventory, new ImplementedInventory.EMPTY(60), ScreenHandlerContext.EMPTY);
	}

	public RuneTableScreenHandler(int syncId, PlayerInventory playerInventory, ImplementedInventory inventory, ScreenHandlerContext context) {
		super(ScreenRegistry.RUNE_TABLE_SCREEN_HANDLER, syncId);
		checkSize(inventory, 60);
		this.context = context;
		this.player = playerInventory.player;
		this.implementedInventory = inventory;

		//some inventories do custom logic when a player opens it.
		inventory.onOpen(playerInventory.player);

		//This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
		//This will not render the background of the slots however, this is the Screens job
		int m;
		int l;
		//Our inventory
		for (m = 0; m < 6; m++)
		{
			for (l = 0; l < 9; l++)
			{
				this.addSlot(new Slot(inventory, l + m * 9, 8 + l * 18, 18 + m * 18));
			}
		}

		for (m = 0; m < 6; m++)
		{
			this.addSlot(new FluxSlot(inventory, m+54, 188, 18 + m * 18));
		}

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

	public RuneTableScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
		this(syncId, playerInventory);
	}


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

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.implementedInventory.canPlayerUse(player);
	}
}
