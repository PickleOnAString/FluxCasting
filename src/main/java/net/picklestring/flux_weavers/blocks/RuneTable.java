package net.picklestring.flux_weavers.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.blocks.entity.RuneTableEntity;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;
import net.picklestring.flux_weavers.utils.ListUtils;
import org.jetbrains.annotations.Nullable;

public class RuneTable extends BlockWithEntity {
	public static final MapCodec<RuneTable> CODEC = createCodec(RuneTable::new);

	public RuneTable(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RuneTableEntity(pos, state);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (!world.isClient) {
			if (player.getStackInHand(Hand.MAIN_HAND).getOrDefault(ItemComponentRegistry.CAN_BIND_SPELLS, false)) {
				RuneTableEntity blockEntity = (RuneTableEntity) world.getBlockEntity(pos);
				ItemStack item = player.getStackInHand(Hand.MAIN_HAND);
				item.set(ItemComponentRegistry.SPELL, ListUtils.cloneItemStackList(blockEntity.getItems()));
				player.setStackInHand(Hand.MAIN_HAND, item);
			} else {
				NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
				if (screenHandlerFactory != null) player.openHandledScreen(screenHandlerFactory);
				return ActionResult.CONSUME;
			}
		}
		return ActionResult.SUCCESS;
	}

	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
}
