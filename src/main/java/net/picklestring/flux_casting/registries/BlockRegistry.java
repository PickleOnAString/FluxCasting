package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.blocks.FluxStoneLeak;
import net.picklestring.flux_casting.blocks.RiftBench;
import net.picklestring.flux_casting.blocks.RuneTable;
import net.picklestring.flux_casting.blocks.entity.RuneTableEntity;

public class BlockRegistry {
	public static final FluxStoneLeak FLUX_STONE_LEAK = new FluxStoneLeak(FabricBlockSettings.create().strength(3, 3).requiresTool());
	public static final RiftBench RIFT_BENCH = new RiftBench(FabricBlockSettings.create());
	public static final RuneTable RUNE_TABLE = new RuneTable(FabricBlockSettings.create());

	public static void Register()
	{
		Registry.register(Registries.BLOCK, Identifier.of(FluxCasting.ModID, "flux_stone_leak"), FLUX_STONE_LEAK);
		Registry.register(Registries.BLOCK, Identifier.of(FluxCasting.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.BLOCK, Identifier.of(FluxCasting.ModID, "rune_table"), RUNE_TABLE);
	}
}
