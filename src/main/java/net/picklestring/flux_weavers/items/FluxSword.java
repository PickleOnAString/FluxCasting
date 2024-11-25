package net.picklestring.flux_weavers.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.items.runes.RuneItem;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.ListUtils;

import java.util.List;

public class FluxSword extends SwordItem {
	public FluxSword(ToolMaterial toolMaterial, Settings settings) {
		super(toolMaterial, settings);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!(attacker instanceof PlayerEntity caster)) return true;

		CastingContext context = new CastingContext();
		context.extraContext.put("Target", target);

		if (!stack.contains(ItemComponentRegistry.SPELL)) return true;
		List<ItemStack> spell = stack.get(ItemComponentRegistry.SPELL);

        CastingContext.cast(ListUtils.listToDefaultedList(spell, ItemStack.EMPTY), caster, context);

		return true;
	}
}
