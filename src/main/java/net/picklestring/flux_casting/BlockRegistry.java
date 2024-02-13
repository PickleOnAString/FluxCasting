package net.picklestring.flux_casting;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.blocks.FluxStoneLeak;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class BlockRegistry {
	public static final FluxStoneLeak FLUX_STONE_LEAK = new FluxStoneLeak(QuiltBlockSettings.create().strength(3, 3).requiresTool());

	public static void Register()
	{
		Registry.register(Registries.BLOCK, new Identifier(FluxCasting.ModID, "flux_stone_leak"), FLUX_STONE_LEAK);
	}
}
