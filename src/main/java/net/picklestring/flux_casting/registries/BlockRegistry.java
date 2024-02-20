package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class BlockRegistry {
	public static final FluxStoneLeak FLUX_STONE_LEAK = new FluxStoneLeak(QuiltBlockSettings.create().strength(3, 3).requiresTool());
	public static final RiftBench RIFT_BENCH = new RiftBench(QuiltBlockSettings.create());
	public static final RuneTable RUNE_TABLE = new RuneTable(QuiltBlockSettings.create());

	public static void Register()
	{
		Registry.register(Registries.BLOCK, new Identifier(FluxCasting.ModID, "flux_stone_leak"), FLUX_STONE_LEAK);
		Registry.register(Registries.BLOCK, new Identifier(FluxCasting.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.BLOCK, new Identifier(FluxCasting.ModID, "rune_table"), RUNE_TABLE);
	}
}
