package net.picklestring.flux_weavers.items.runes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.RaycastUtils;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class FaceFinderRune extends RuneItem {
	public FaceFinderRune(Settings settings) {
		super(
			settings,
			new Type[][] {
				new Type[] {
					Vector3.class
				},
				new Type[] {
					Vector3.class
				}
			},
			new Type[] {
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

		lookVec = loadVec3FromData(0, runeIndex, caster);
		if (lookVec == null) return new Object[] { null };

		positionVec = loadVec3FromData(1, runeIndex, caster);
		if (positionVec == null) return new Object[] { null };

		Vec3d vec3d = positionVec.VectorToVec3d();
		Vec3d vec3d2 = lookVec.VectorToVec3d();
		Vec3d vec3d3 = vec3d.add(vec3d2.x * 100, vec3d2.y * 100, vec3d2.z * 100);

		BlockHitResult hit = RaycastUtils.raycast(new BlockStateRaycastContext(vec3d, vec3d3, BlockStatePredicate.ANY
			.and(BlockStatePredicate.forBlock(Blocks.AIR).negate())
			.and(BlockStatePredicate.forBlock(Blocks.WATER).negate())
			.and(BlockStatePredicate.forBlock(Blocks.LAVA).negate())
			.and(BlockStatePredicate.forBlock(Blocks.SHORT_GRASS).negate())
			.and(BlockStatePredicate.forBlock(Blocks.TALL_GRASS).negate())), world);

		if (hit.getSide() == Direction.UP) FluxWeavers.LOGGER.info("Test");


		return new Object[]{
			Vector3.Vec3dToVector3(Vec3d.of(hit.getSide().getVector()))
		};
	}
}
