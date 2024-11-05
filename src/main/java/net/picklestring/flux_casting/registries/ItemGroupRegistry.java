package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;

public class ItemGroupRegistry {
	private static final ItemGroup MISC_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(ItemRegistry.BLANK_RUNE))
		.displayName(Text.translatable("itemGroup.flux_casting.misc_group"))
		.entries((context, entries) -> {
			entries.add(ItemRegistry.BLANK_RUNE);
			entries.add(ItemRegistry.FLUX_BOTTLE);
			entries.add(ItemRegistry.BOTTLE_O_SCARRED_STONE);
			entries.add(ItemRegistry.SCARRED_STONE);
			entries.add(ItemRegistry.RIFT_BENCH);
		})
		.build();

	public static void Register()
	{
		Registry.register(Registries.ITEM_GROUP, Identifier.of(FluxCasting.ModID, "misc_group"), MISC_GROUP);
	}
}
