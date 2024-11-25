package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class VectorMultiplicationRune extends RuneItem {
	public VectorMultiplicationRune(Settings settings) {
		super(
			settings,
			new Type[][]{
				new Type[]{
					Vector3.class
				},
				new Type[]{
					Vector3.class
				}
			},
			new Type[] {
				Vector3.class
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
		if (!context.isStack) {
			executeInserters(inventory, runeIndex, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, runeIndex, 0);
			stringPartOrStackPop(context, inventory, runeIndex, 1);
		}

		Vector3 vecOne = null;
		Vector3 vecTwo = null;

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };
		if (isDataNull(caster, inventory, runeIndex, 1)) return new Object[] { null };

		if (data[0] instanceof String) {
			vecOne = stringToVec((String)data[0], caster, runeIndex, 0);
			if (vecOne == null) {
				return new Object[] { null };
			}
		} else if (data[0] instanceof Vector3) {
			vecOne = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, 0);
			return new Object[] { null };
		}

		if (data[1] instanceof String) {
			vecTwo = stringToVec((String)data[1], caster, runeIndex, 1);
			if (vecTwo == null) {
				return new Object[] { null };
			}
		} else if (data[1] instanceof Vector3) {
			vecTwo = (Vector3)data[1];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, 1);
			return new Object[] { null };
		}

		return new Object[] {
			vecOne.multiply(vecTwo)
		};
	}
}
