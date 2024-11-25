package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.CopyUtils;

import java.lang.reflect.Type;
import java.util.Stack;

public class PointerMultiplicationRune extends RuneItem {
	public PointerMultiplicationRune(Settings settings) {
		super(
			settings,
			new Type[][] {
				new Type[] {
					Integer.class
				}
			},
			new Type[] {
				Object.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/flash_fire_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return;
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		data[0] = Integer.valueOf(0);

		if (!context.isStack) {
			executeInserters(inventory, runeIndex, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, runeIndex, 0);
		}

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };

		int stackIndex = loadIntegerFromData(0, runeIndex, caster);

		FluxWeavers.LOGGER.info(context.stack.size()-1-stackIndex+" copy index");
		CastingContext.PrintStack(context);

		return new Object[] {
			CopyUtils.Copy(context.stack.get(context.stack.size()-1-stackIndex))
		};
	}
}
