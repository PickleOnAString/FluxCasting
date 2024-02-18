package net.picklestring.flux_casting.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.picklestring.flux_casting.registries.BlockEntityRegistry;
import net.picklestring.flux_casting.blocks.entity.FluxStoneLeakEntity;
import org.jetbrains.annotations.Nullable;

public class FluxStoneLeak extends BlockWithEntity {
	public FluxStoneLeak(Settings settings) {
		super(settings);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		// With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
		return BlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new FluxStoneLeakEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return world.isClient ? null : checkType(type, BlockEntityRegistry.FLUX_STONE_LEAK_ENTITY, FluxStoneLeakEntity::tick);
	}
}
