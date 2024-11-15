package net.picklestring.flux_weavers.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.blocks.FluxStoneLeak;
import net.picklestring.flux_weavers.blocks.RiftBench;
import net.picklestring.flux_weavers.blocks.RuneTable;

public class BlockRegistry {
	public static final FluxStoneLeak FLUX_STONE_LEAK = new FluxStoneLeak(FabricBlockSettings.create().strength(3, 3).requiresTool());
	public static final RiftBench RIFT_BENCH = new RiftBench(FabricBlockSettings.create());
	public static final RuneTable RUNE_TABLE = new RuneTable(FabricBlockSettings.create());

	public static void Register()
	{
		Registry.register(Registries.BLOCK, Identifier.of(FluxWeavers.ModID, "flux_stone_leak"), FLUX_STONE_LEAK);
		Registry.register(Registries.BLOCK, Identifier.of(FluxWeavers.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.BLOCK, Identifier.of(FluxWeavers.ModID, "rune_table"), RUNE_TABLE);
	}
}
