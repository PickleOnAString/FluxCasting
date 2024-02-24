package net.picklestring.flux_casting.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;
import org.jetbrains.annotations.Nullable;

public class RuneTable extends BlockWithEntity {
	public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
	public RuneTable(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(TRIGGERED, Boolean.FALSE));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		// Add the block state property
		builder.add(TRIGGERED);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RuneTableEntity(pos, state);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
			if (screenHandlerFactory != null) player.openHandledScreen(screenHandlerFactory);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
		boolean powered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up());
		boolean justPowered = state.get(TRIGGERED);
		if (powered && !justPowered)
		{
			((RuneTableEntity)world.getBlockEntity(pos)).executeRunes();
			world.setBlockState(pos, state.with(TRIGGERED, Boolean.TRUE));
		}
		else if (!powered && justPowered)
		{
			world.setBlockState(pos, state.with(TRIGGERED, Boolean.FALSE));
		}
	}

	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
	}
}
