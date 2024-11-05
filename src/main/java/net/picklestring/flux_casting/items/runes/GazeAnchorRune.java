package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.utils.CastingContext;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;

public class GazeAnchorRune extends RuneItem {
	public GazeAnchorRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{LivingEntity.class}
			},
			new Type[]{
				Vector3.class
			},
			Identifier.of(FluxCasting.ModID, "textures/gui/rune_overlay/gaze_anchor_rune_overlay.png")
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

		LivingEntity entity = null;
		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };

		if (data[0] instanceof LivingEntity) {
			entity = (LivingEntity)data[0];
		} else {
			sendMisMatchedTypeError(caster, runeIndex, 0);
			return new Object[] { null };
		}

		data = new Object[data.length];
		return new Object[]{
			Vector3.Vec3dToVector3(entity.getEyePos())
		};
	}
}
