package net.picklestring.flux_casting.registries;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.BottleOScarredStone;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ItemRegistry {
	public static final Item EXAMPLE_ITEM = new Item(new QuiltItemSettings());
	public static final Item BLANK_RUNE = new Item(new QuiltItemSettings());
	public static final Item FLUX_BOTTLE = new Item(new QuiltItemSettings());
	public static final Item SCARRED_STONE = new Item(new QuiltItemSettings());
	public static final BottleOScarredStone BOTTLE_O_SCARRED_STONE = new BottleOScarredStone(new QuiltItemSettings());

	public static void Register()
	{
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "example_item"), EXAMPLE_ITEM);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "blank_rune"), BLANK_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "flux_bottle"), FLUX_BOTTLE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "scarred_stone"), SCARRED_STONE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "bottle_o_scarred_stone"), BOTTLE_O_SCARRED_STONE);
	}
}
