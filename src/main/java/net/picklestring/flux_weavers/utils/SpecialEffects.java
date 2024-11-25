package net.picklestring.flux_weavers.utils;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.picklestring.flux_weavers.registries.ParticleRegistry;

import java.util.Random;

public class SpecialEffects {
	public void FluxBlockBoarder(ServerWorld world, BlockPos pos) {
		int randX = (int) Math.round(Math.random());
		int randY = (int) Math.round(Math.random());
		int randZ = (int) Math.round(Math.random());
		for (int i = 0; i < 100; i++) {
			world.spawnParticles(ParticleRegistry.FLUX_STAR, pos.getX(), pos.getY(), pos.getZ(), 1, 0, 0, 0, 0);
		}
	}
}
