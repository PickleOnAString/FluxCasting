package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;

import java.lang.reflect.Type;

public class FluxValidatorRune extends RuneItem {
	public FluxValidatorRune(Settings settings) {
		super(
			settings,
			new Type[][] {
				new Type[] {
					Object.class
				}
			},
			new Type[] {
				Object.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/numerical_nexus_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return;
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		if (!context.isStack) {
			executeInserters(inventory, runeIndex, caster, pos, world, context);
		}
		else {
			data[0] = context.stack.pop();
		}

		if (data[0] == null) {
			FluxWeavers.LOGGER.info("isNull?");
			context.isCanceled = true;
			return new Object[] { null };
		}
		Object val = data[0];

		data = new Object[data.length];

		return new Object[] {
			val
		};
	}
}
