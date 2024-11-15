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

public class SpatialSliceRune extends RuneItem {
	public SpatialSliceRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{Vector3.class}
			},
			new Type[]{
				Double.class,
				Double.class,
				Double.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/extract_x_rune_overlay.png")
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
		}
		Vector3 posData = null;

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };

		if (data[0] instanceof String) {
			String[] strs = ((String) data[0]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, runeIndex, 0);
				return new Object[] { null };
			};
			posData = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[0] instanceof Vector3) {
			posData = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, 0);
			return new Object[] { null };
		}

		data = new Object[dataFormat.length];
		return new Object[]{
			posData.getX(),
			posData.getY(),
			posData.getZ()
		};
	}
}
