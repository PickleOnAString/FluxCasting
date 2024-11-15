package net.picklestring.flux_weavers.items.runes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class TargetPerceptionRune extends RuneItem {
	public TargetPerceptionRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{
					Vector3.class
				},
				new Type[]{
					Vector3.class
				}
			},
			new Type[]{
				Vector3.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/target_perception_rune_overlay.png")
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

		Vector3 lookVec = null;
		Vector3 positionVec = null;

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };
		if (isDataNull(caster, inventory, runeIndex, 1)) return new Object[] { null };

		if (data[0] instanceof String) {
			String[] strs = ((String) data[0]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, runeIndex, 0);
				return new Object[] { null };
			};
			lookVec = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[0] instanceof Vector3) {
			lookVec = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, 0);
			return new Object[] { null };
		}

		if (data[1] instanceof String) {
			String[] strs = ((String) data[1]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, runeIndex, 1);
				return new Object[] { null };
			};
			positionVec = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[1] instanceof Vector3) {
			positionVec = (Vector3)data[1];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, 1);
			return new Object[] { null };
		}

		Vec3d vec3d = positionVec.VectorToVec3d();
		Vec3d vec3d2 = lookVec.VectorToVec3d();
		Vec3d vec3d3 = vec3d.add(vec3d2.x * 100, vec3d2.y * 100, vec3d2.z * 100);

		BlockHitResult hit = raycast(new BlockStateRaycastContext(vec3d, vec3d3, BlockStatePredicate.ANY
			.and(BlockStatePredicate.forBlock(Blocks.AIR).negate())
			.and(BlockStatePredicate.forBlock(Blocks.WATER).negate())
			.and(BlockStatePredicate.forBlock(Blocks.LAVA).negate())
			.and(BlockStatePredicate.forBlock(Blocks.SHORT_GRASS).negate())
			.and(BlockStatePredicate.forBlock(Blocks.TALL_GRASS).negate())), world);

		FluxWeavers.LOGGER.info("hit pos: "+hit.getBlockPos().toString());

        return new Object[]{
			Vector3.Vec3dToVector3(hit.getPos())
		};
	}

	BlockHitResult raycast(BlockStateRaycastContext context, World world) {
		return (BlockHitResult) BlockView.raycast(context.getStart(), context.getEnd(), context, (contextx, pos) -> {
			BlockState blockState = world.getBlockState(pos);
			Vec3d vec3d = contextx.getStart().subtract(contextx.getEnd());
			return contextx.getStatePredicate().test(blockState) ? new BlockHitResult(pos.toCenterPos(), Direction.getFacing(vec3d.x, vec3d.y, vec3d.z), BlockPos.ofFloored(pos.toCenterPos()), false) : null;
		}, (contextx) -> {
			Vec3d vec3d = contextx.getStart().subtract(contextx.getEnd());
			return BlockHitResult.createMissed(contextx.getEnd(), Direction.getFacing(vec3d.x, vec3d.y, vec3d.z), BlockPos.ofFloored(contextx.getEnd()));
		});
	}
}
