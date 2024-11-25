package net.picklestring.flux_weavers.utils;

import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RaycastUtils {
	public static BlockHitResult raycast(BlockStateRaycastContext context, World world) {
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
