package net.picklestring.flux_casting.registries;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.FluxStoneLeakEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.picklestring.flux_casting.blocks.entity.RiftBenchEntity;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class BlockEntityRegistry {
	public static final BlockEntityType<FluxStoneLeakEntity> FLUX_STONE_LEAK_ENTITY = QuiltBlockEntityTypeBuilder.create(FluxStoneLeakEntity::new, BlockRegistry.FLUX_STONE_LEAK).build();
	public static final BlockEntityType<RiftBenchEntity> RIFT_BENCH_ENTITY = QuiltBlockEntityTypeBuilder.create(RiftBenchEntity::new, BlockRegistry.RIFT_BENCH).build();

	public static void Register()
	{
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FluxCasting.ModID, "flux_stone_leak_entity"), FLUX_STONE_LEAK_ENTITY);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FluxCasting.ModID, "rift_bench_entity"), RIFT_BENCH_ENTITY);
	}
}
