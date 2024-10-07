package net.picklestring.flux_casting.items.runes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TargetPerceptionRune extends RuneItem {
	public TargetPerceptionRune(Settings settings) {
		super(settings, new Type[][]{ new Type[]{Vector3.class}, new Type[]{Vector3.class} },
			Vector3.class,
			new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/target_perception_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, runeIndex, caster, pos, world);
		Vector3 lookVec = getDataOrDefault(0, null, Vector3.class);
		Vector3 positionVec = getDataOrDefault(1, null, Vector3.class);

		Vec3d vec3d = positionVec.VectorToVec3d();
		Vec3d vec3d2 = lookVec.VectorToVec3d();
		Vec3d vec3d3 = vec3d.add(vec3d2.x * 100, vec3d2.y * 100, vec3d2.z * 100);

		FluxCasting.LOGGER.info("look vec: "+vec3d);
		FluxCasting.LOGGER.info("pos vec: "+vec3d3);

		if (lookVec == null) {
			caster.sendMessage(Text.literal("Rune at: index "+runeIndex+", Vector3 at Index 0, is null").formatted(Formatting.RED), false);
		}
		if (positionVec == null) {
			caster.sendMessage(Text.literal("Rune at: index "+runeIndex+", Vector3 at Index 1, is null").formatted(Formatting.RED), false);
		}

		BlockHitResult hit = raycast(new BlockStateRaycastContext(vec3d, vec3d3, BlockStatePredicate.ANY
			.and(BlockStatePredicate.forBlock(Blocks.AIR).negate())
			.and(BlockStatePredicate.forBlock(Blocks.WATER).negate())
			.and(BlockStatePredicate.forBlock(Blocks.LAVA).negate())
			.and(BlockStatePredicate.forBlock(Blocks.GRASS).negate())
			.and(BlockStatePredicate.forBlock(Blocks.TALL_GRASS).negate())), world);

		FluxCasting.LOGGER.info("hit pos: "+hit.getBlockPos().toString());

        return Vector3.Vec3dToVector3(hit.getPos());
	}

	BlockHitResult raycast(BlockStateRaycastContext context, World world) {
		return (BlockHitResult) BlockView.raycast(context.getStart(), context.getEnd(), context, (contextx, pos) -> {
			BlockState blockState = world.getBlockState(pos);
			Vec3d vec3d = contextx.getStart().subtract(contextx.getEnd());
			return contextx.getStatePredicate().test(blockState) ? new BlockHitResult(pos.ofCenter(), Direction.getFacing(vec3d.x, vec3d.y, vec3d.z), BlockPos.fromPosition(pos.ofCenter()), false) : null;
		}, (contextx) -> {
			Vec3d vec3d = contextx.getStart().subtract(contextx.getEnd());
			return BlockHitResult.createMissed(contextx.getEnd(), Direction.getFacing(vec3d.x, vec3d.y, vec3d.z), BlockPos.fromPosition(contextx.getEnd()));
		});
	}
}
