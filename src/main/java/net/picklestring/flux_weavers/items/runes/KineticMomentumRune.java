package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.LivingEntity;
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

public class KineticMomentumRune extends RuneItem{
	public KineticMomentumRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{
					LivingEntity.class
				},
				new Type[]{
					Vector3.class
				},
			},
			null,
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/kinetic_momentum_rune_overlay.png")
		);
		//data = DefaultedList.ofSize(60, null).stream().toList();
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

		LivingEntity entity = null;
		Vector3 vec = null;

		if (isDataNull(caster, inventory, index, 0)) return;
		if (isDataNull(caster, inventory, index, 1)) return;

		if (data[0] instanceof LivingEntity) {
			entity = (LivingEntity)data[0];
		} else {
			sendMisMatchedTypeError(caster, index, 0);
			return;
		}

		if (data[1] instanceof Vector3) {
			vec = (Vector3)data[1];
		} else {
			sendMisMatchedTypeError(caster, index, 1);
			return;
		}

		FluxWeavers.LOGGER.info(vec.VectorToVec3d().x+", "+vec.VectorToVec3d().y+", "+vec.VectorToVec3d().z);
		entity.addVelocity(vec.VectorToVec3d());
		entity.velocityModified = true;

		consumeFlux(Math.round((float)Math.floor(12*(float)vec.getMagnitude())), caster);

		FluxWeavers.LOGGER.info("Huhhh momentum?");

		data = new Object[data.length];
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
