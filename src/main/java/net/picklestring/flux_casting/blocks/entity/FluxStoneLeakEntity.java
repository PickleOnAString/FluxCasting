package net.picklestring.flux_casting.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.picklestring.flux_casting.*;
import net.picklestring.flux_casting.blocks.FluxStoneLeak;

import java.util.List;

public class FluxStoneLeakEntity extends BlockEntity {
    public FluxStoneLeakEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLUX_STONE_LEAK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, FluxStoneLeakEntity be) {
		boolean detectedEntity = false;
        for (ItemEntity itemEntity : FluxStoneLeakEntity.getItemsAbovePos(world, pos)) {
			detectedEntity = true;
			ItemStack stack = itemEntity.getStack();
			if (stack.getNbt() == null) stack.setNbt(new NbtCompound());
			if (!stack.getNbt().contains("infused_flux")) stack.getNbt().putInt("infused_flux", 0);
			stack.getNbt().putInt("infused_flux", stack.getNbt().getInt("infused_flux")+1);

			if (stack.getNbt().getInt("infused_flux") >= 2000) itemEntity.setStack(new ItemStack(ItemRegistry.FLUX_BOTTLE, stack.getCount()));
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
