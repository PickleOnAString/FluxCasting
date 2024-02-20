package net.picklestring.flux_casting.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;
import org.jetbrains.annotations.Nullable;

public class RuneTable extends BlockWithEntity {
	public RuneTable(Settings settings) {
		super(settings);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RuneTableEntity(pos, state);
	}
}
