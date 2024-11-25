package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;
import java.util.EnumSet;

public class TeleportationRune extends RuneItem{
	public TeleportationRune(Settings settings) {
		super(
			settings,
			new Type[][]{
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
		}

		Vector3 vecOne = null;

		if (isDataNull(caster, inventory, index, 0)) return;

		if (data[0] instanceof String) {
			vecOne = stringToVec((String)data[0], caster, index, 0);
			if (vecOne == null) {
				return;
			}
		} else if (data[0] instanceof Vector3) {
			vecOne = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, index, 0);
			return;
		}
		if (Math.abs(vecOne.getMagnitude()-Vector3.Vec3dToVector3(caster.getPos()).getMagnitude()) > 20) {
			caster.sendMessage(Text.translatable("rune.flux_weavers.error.teleportation.out_of_range"));
			return;
		}
		if (world.isClient) return;

		consumeFlux((int)Math.abs(vecOne.getMagnitude()-Vector3.Vec3dToVector3(caster.getPos()).getMagnitude())*5, caster);
		caster.teleport((ServerWorld)world, vecOne.getX(), vecOne.getY(), vecOne.getZ(), EnumSet.noneOf(PositionFlag.class), caster.getYaw(), caster.getPitch());
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
