package net.picklestring.flux_weavers.items.runes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class VerdantBloomRune extends RuneItem {
	public VerdantBloomRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[] {
					Vector3.class
				}
			},
			null,
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/kinetic_momentum_rune_overlay.png")
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

		if (isDataNull(caster, inventory, index, 0)) return;

		Vector3 posVec = null;

		if (data[0] instanceof String) {
			String[] strs = ((String) data[0]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, index, 0);
				return;
			};
			posVec = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[0] instanceof Vector3) {
			posVec = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, index, 0);
			return;
		}

		BlockState blockState = world.getBlockState(BlockPos.ofFloored(posVec.VectorToVec3d()));
		Block block = blockState.getBlock();
		if (block instanceof Fertilizable fertilizable) {
			if (fertilizable.isFertilizable(world, BlockPos.ofFloored(posVec.VectorToVec3d()), blockState)) {
				if (fertilizable.canGrow(world, world.random, BlockPos.ofFloored(posVec.VectorToVec3d()), blockState)) {
					if (!world.isClient) {
						fertilizable.grow((ServerWorld)world, world.random, BlockPos.ofFloored(posVec.VectorToVec3d()), blockState);
					}
				}
			}
		}

		consumeFlux(10, caster);
		data = new Object[data.length];
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
