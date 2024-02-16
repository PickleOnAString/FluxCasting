package net.picklestring.flux_casting;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupRegistry {
	private static final ItemGroup MISC_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(ItemRegistry.BLANK_RUNE))
		.name(Text.translatable("itemGroup.flux_casting.misc_group"))
		.entries((context, entries) -> {
			entries.addItem(ItemRegistry.BLANK_RUNE);
			entries.addItem(ItemRegistry.FLUX_BOTTLE);
			entries.addItem(ItemRegistry.BOTTLE_O_SCARRED_STONE);
			entries.addItem(ItemRegistry.SCARRED_STONE);
		})
		.build();

	public static void Register()
	{
		Registry.register(Registries.ITEM_GROUP, new Identifier(FluxCasting.ModID, "misc_group"), MISC_GROUP);
	}
}
