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

public class TimeReversalRune extends RuneItem {
	public TimeReversalRune(Settings settings) {
		super(
			settings,
			new Type[][] {
				new Type[] {
					Number.class
				},
				new Type[] {
					Boolean.class
				}
			},
			null,
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/extract_x_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		data[0] = Boolean.TRUE;
		if (!context.isStack) {
			executeInserters(inventory, index, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, index, 0);
			stringPartOrStackPop(context, inventory, index, 1);
		}

		if (isDataNull(caster, inventory, index, 0)) return;
		if (isDataNull(caster, inventory, index, 1)) return;

		int revAmount = loadNumberFromData(0, index, caster).intValue();
		Boolean shouldRun = loadBooleanFromData(1, index, caster);

		if ((shouldRun)) context.currentIndex -= revAmount;
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
