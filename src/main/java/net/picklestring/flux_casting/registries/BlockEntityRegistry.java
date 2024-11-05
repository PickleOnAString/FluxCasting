package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.entity.FluxStoneLeakEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.picklestring.flux_casting.blocks.entity.RiftBenchEntity;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;

public class BlockEntityRegistry {
	public static final BlockEntityType<FluxStoneLeakEntity> FLUX_STONE_LEAK_ENTITY = FabricBlockEntityTypeBuilder.create(FluxStoneLeakEntity::new, BlockRegistry.FLUX_STONE_LEAK).build();
	public static final BlockEntityType<RiftBenchEntity> RIFT_BENCH_ENTITY = FabricBlockEntityTypeBuilder.create(RiftBenchEntity::new, BlockRegistry.RIFT_BENCH).build();
	public static final BlockEntityType<RuneTableEntity> RUNE_TABLE_ENTITY = FabricBlockEntityTypeBuilder.create(RuneTableEntity::new, BlockRegistry.RUNE_TABLE).build();

	public static void Register()
	{
		Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(FluxCasting.ModID, "flux_stone_leak_entity"), FLUX_STONE_LEAK_ENTITY);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(FluxCasting.ModID, "rift_bench_entity"), RIFT_BENCH_ENTITY);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(FluxCasting.ModID, "rune_table_entity"), RUNE_TABLE_ENTITY);
	}
}
