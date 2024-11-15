package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class FlashFireRune extends RuneItem {
	public FlashFireRune(Settings settings) {
		super(settings,
			new Type[][] {
				new Type[] {
					Vector3.class
				},
				new Type[] {
					Vector3.class
				}
			},
			null,
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/flash_fire_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		if (!context.isStack) {
			executeInserters(inventory, index, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, index, 0);
			stringPartOrStackPop(context, inventory, index, 1);
		}

		if (isDataNull(caster, inventory, index, 0)) return;
		if (isDataNull(caster, inventory, index, 1)) return;

		Vector3 lookVec = null;
		Vector3 posVec = null;

		if (data[0] instanceof String) {
			String[] strs = ((String) data[0]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, index, 0);
				return;
			};
			lookVec = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[0] instanceof Vector3) {
			lookVec = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, index, 0);
			return;
		}

		if (data[1] instanceof String) {
			String[] strs = ((String) data[1]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, index, 1);
				return;
			};
			posVec = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[1] instanceof Vector3) {
			posVec = (Vector3)data[1];
		}else {
			sendMisMatchedTypeError(caster, index, 1);
			return;
		}

		FireballEntity fireball = EntityType.FIREBALL.create(world);
		fireball.updatePosition(posVec.getX(), posVec.getY(), posVec.getZ());
		fireball.addVelocity(lookVec.VectorToVec3d());
		world.spawnEntity(fireball);

		consumeFlux(Math.round((float)Math.floor(32*(float)lookVec.getMagnitude())), caster);

		data = new Object[dataFormat.length];
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
