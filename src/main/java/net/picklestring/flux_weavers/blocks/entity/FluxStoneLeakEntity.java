package net.picklestring.flux_weavers.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.registries.BlockEntityRegistry;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;
import net.picklestring.flux_weavers.registries.ItemRegistry;
import net.picklestring.flux_weavers.registries.ParticleRegistry;

import java.util.List;

public class FluxStoneLeakEntity extends BlockEntity {
	private int fluxLeft = 16000;

    public FluxStoneLeakEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLUX_STONE_LEAK_ENTITY, pos, state);
    }

	@Override
	public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
		// Save the current value of the number to the nbt
		nbt.putInt("flux_left", fluxLeft);

		super.writeNbt(nbt, registryLookup);
	}

	@Override
	public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
		super.readNbt(nbt, registryLookup);

		fluxLeft = nbt.getInt("flux_left");
	}

    public static void tick(World world, BlockPos pos, BlockState state, FluxStoneLeakEntity be) {
		boolean detectedEntity = false;
        for (ItemEntity itemEntity : FluxStoneLeakEntity.getItemsAbovePos(world, pos)) {
			detectedEntity = true;
			ItemStack stack = itemEntity.getStack();
			if (stack.getItem() != ItemRegistry.BOTTLE_O_SCARRED_STONE) return;
			int flux = stack.get(ItemComponentRegistry.FLUX);
			stack.set(ItemComponentRegistry.FLUX, flux+1);

			if (flux+1 >= 2000)
			{
				if (stack.getCount() > be.fluxLeft)
				{
					itemEntity.dropStack(new ItemStack(ItemRegistry.FLUX_BOTTLE, be.fluxLeft));
					stack.setCount(stack.getCount()-be.fluxLeft);
					be.fluxLeft = 0;
				}
				else
				{
					be.fluxLeft -= stack.getCount();
					itemEntity.setStack(new ItemStack(ItemRegistry.FLUX_BOTTLE, stack.getCount()));
				}
				if (be.fluxLeft <= 0) world.setBlockState(pos, Blocks.STONE.getDefaultState());
				return;
			}
			be.fluxLeft -= stack.getCount();
			if (be.fluxLeft <= 0) world.setBlockState(pos, Blocks.STONE.getDefaultState());
        }

		if (detectedEntity && (Math.random() >= 0.75))
		{
			((ServerWorld)world).spawnParticles(ParticleRegistry.FLUX_STAR, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 1, 0.25D, 0.25D, 0.25D, 0.1);
		}
    }

    private static List<ItemEntity> getItemsAbovePos(World world, BlockPos pos) {
        VoxelShape blockAboveArea = VoxelShapes.cuboid(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1);

        return world.getEntitiesByClass(ItemEntity.class, blockAboveArea.getBoundingBox(), (ItemEntity entity) -> (entity.getStack().isOf(ItemRegistry.BOTTLE_O_SCARRED_STONE)));
    }
}
