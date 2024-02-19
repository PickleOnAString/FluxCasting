package net.picklestring.flux_casting.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.RiftBenchEntity;
import net.picklestring.flux_casting.registries.BlockEntityRegistry;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class RiftBench extends HorizontalFacingBlock implements BlockEntityProvider {
	public static final EnumProperty<Part> PART = EnumProperty.of("part", Part.class);
	public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

	public RiftBench(Settings settings) {
		super(settings.nonOpaque());
		setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(ACTIVE, false));
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return state.get(PART) == Part.MAIN ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		// Add the block state property
		builder.add(PART);
		builder.add(Properties.HORIZONTAL_FACING);
		builder.add(ACTIVE);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		if (!world.isClient) {
			BlockPos blockPos = pos.offset(state.get(FACING));
			world.setBlockState(blockPos, state.with(PART, Part.SIDE), Block.NOTIFY_ALL);
			world.updateNeighbors(pos, Blocks.AIR);
			state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		FluxCasting.LOGGER.info("used");
		if (!world.isClient) {
			FluxCasting.LOGGER.info("on server");
			//This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
			//a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
			NamedScreenHandlerFactory screenHandlerFactory = state.get(PART) == Part.MAIN ? state.createScreenHandlerFactory(world, pos) : world.getBlockState(pos.offset(getDirectionTowardsOtherPart(state.get(PART), state.get(FACING)))).createScreenHandlerFactory(world, pos.offset(getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))));

			if (screenHandlerFactory != null) {
				FluxCasting.LOGGER.info("opening screen");
				//With this call the server will request the client to open the appropriate Screenhandler
				player.openHandledScreen(screenHandlerFactory);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getPlayerFacing().rotateClockwise(Direction.Axis.Y);
		BlockPos blockPos = ctx.getBlockPos();
		BlockPos blockPos2 = blockPos.offset(direction);
		World world = ctx.getWorld();
		return world.getBlockState(blockPos2).canReplace(ctx) && world.getWorldBorder().contains(blockPos2) ? this.getDefaultState().with(FACING, direction) : null;
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient && player.isCreative()) {
			Part part = state.get(PART);
			if (part == Part.MAIN) {
				BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(part, state.get(FACING)));
				BlockState blockState = world.getBlockState(blockPos);
				if (blockState.isOf(this) && blockState.get(PART) == Part.SIDE) {
					world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
					world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
				}
			}
		}

		super.onBreak(world, pos, state, player);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))) {
			return neighborState.isOf(this) && neighborState.get(PART) != state.get(PART) ? state : Blocks.AIR.getDefaultState();
		} else {
			return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		}
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return world.isClient ? null : (BlockEntityTicker)checkType(type, BlockEntityRegistry.RIFT_BENCH_ENTITY, RiftBenchEntity::tick);
	}

	@Nullable
	protected static <A extends BlockEntity> BlockEntityTicker<? super RiftBenchEntity> checkType(BlockEntityType<A> givenType, BlockEntityType<RiftBenchEntity> expectedType, BlockEntityTicker<? super RiftBenchEntity> ticker) {
		return expectedType == givenType ? ticker : null;
	}

	private static Direction getDirectionTowardsOtherPart(Part part, Direction direction) {
		return part == Part.MAIN ? direction : direction.getOpposite();
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RiftBenchEntity(pos, state);
	}

	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
	}

	public enum Part implements StringIdentifiable {
		MAIN,
		SIDE;

		@Override
		public String asString() {
			return this.name().toLowerCase();
		}
	}
}
