package net.picklestring.flux_casting.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;
import net.picklestring.flux_casting.registries.BlockRegistry;
import net.picklestring.flux_casting.registries.ItemComponentRegistry;
import net.picklestring.flux_casting.registries.ItemRegistry;
import net.picklestring.flux_casting.utils.ListUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SpellHolder extends Item {
	public SpellHolder(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (!context.getWorld().isClient) {
			if (context.getPlayer().isSneaking() && !context.getWorld().getBlockState(context.getBlockPos()).isOf(BlockRegistry.RUNE_TABLE)) {
				context.getStack().remove(ItemComponentRegistry.SPELL);
            }
			else {
				if (!context.getWorld().getBlockState(context.getBlockPos()).isOf(BlockRegistry.RUNE_TABLE))
					return ActionResult.PASS;
				if (!context.getStack().contains(ItemComponentRegistry.SPELL)) return ActionResult.PASS;
				PlayerEntity player = context.getPlayer();
				List<ItemStack> spell = ListUtils.cloneItemStackList(context.getStack().get(ItemComponentRegistry.SPELL));
				if (!((RuneTableEntity) context.getWorld().getBlockEntity(context.getBlockPos())).isEmpty()) {
					player.sendMessage(Text.translatable("item.flux_casting.spell_holder.table_not_empty"));
					return ActionResult.SUCCESS;
				}
				boolean hasItems = true;
				for (int i = 0; i < player.getInventory().size(); i++) {
					if (spell.get(i).getItem() == Items.AIR) continue;
					if (!player.getInventory().containsAny(Collections.singleton(spell.get(i).getItem()))) {
						hasItems = false;
						player.sendMessage(Text.translatable("item.flux_casting.spell_holder.missing_rune", Text.translatable(spell.get(i).getItem().getTranslationKey())));
						break;
					}
				}
				if (!hasItems) return ActionResult.SUCCESS;

				for (int i = 0; i < context.getStack().get(ItemComponentRegistry.SPELL).size(); i++) {
					for (int z = 0; z < player.getInventory().size(); z++) {
						if (player.getInventory().getStack(z).getItem() == spell.get(i).getItem()) {
							player.getInventory().removeStack(z, 1);
							break;
						}
					}
				}

				for (int i = 0; i < context.getStack().get(ItemComponentRegistry.SPELL).size(); i++) {
					((RuneTableEntity) context.getWorld().getBlockEntity(context.getBlockPos())).setStack(i, spell.get(i));
				}

            }
            return ActionResult.SUCCESS;
        }
		return ActionResult.PASS;
	}
}
