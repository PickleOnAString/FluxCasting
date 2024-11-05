package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.BottleOScarredStone;
import net.picklestring.flux_casting.items.FluxWand;
import net.picklestring.flux_casting.items.Ring;
import net.picklestring.flux_casting.items.SpellHolder;
import net.picklestring.flux_casting.items.runes.*;

import java.sql.Ref;

public class ItemRegistry {
	public static final Item BLANK_RUNE = new Item(new Item.Settings());
	public static final Item FLUX_BOTTLE = new Item(new Item.Settings());
	public static final Item SCARRED_STONE = new Item(new Item.Settings());
	public static final BottleOScarredStone BOTTLE_O_SCARRED_STONE = new BottleOScarredStone(new Item.Settings()
		.component(ItemComponentRegistry.FLUX, 0)
		.component(ItemComponentRegistry.FLUX_AVAILABILITY, 0.05f)
		.component(ItemComponentRegistry.CAN_CONSUME_FLUX, false));
	public static final Ring RING = new Ring(new Item.Settings());
	public static final FluxWand FLUX_WAND = new FluxWand(new Item.Settings()
		.component(ItemComponentRegistry.CAN_BIND_SPELLS, true));
	public static final SpellHolder SPELL_HOLDER = new SpellHolder(new Item.Settings()
		.component(ItemComponentRegistry.CAN_BIND_SPELLS, true));
	public static final BlockItem RIFT_BENCH = new BlockItem(BlockRegistry.RIFT_BENCH, new Item.Settings());
	public static final BlockItem RUNE_TABLE = new BlockItem(BlockRegistry.RUNE_TABLE, new Item.Settings());

	public static void Register() {
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "blank_rune"), BLANK_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "flux_bottle"), FLUX_BOTTLE);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "scarred_stone"), SCARRED_STONE);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "bottle_o_scarred_stone"), BOTTLE_O_SCARRED_STONE);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "ring"), RING);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "flux_wand"), FLUX_WAND);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "spell_holder"), SPELL_HOLDER);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.ITEM, Identifier.of(FluxCasting.ModID, "rune_table"), RUNE_TABLE);
	}

	public static void GenerateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(BLANK_RUNE, Models.GENERATED);
		itemModelGenerator.register(FLUX_BOTTLE, Models.GENERATED);
		itemModelGenerator.register(SCARRED_STONE, Models.GENERATED);
		itemModelGenerator.register(BOTTLE_O_SCARRED_STONE, Models.GENERATED);
		itemModelGenerator.register(FLUX_WAND, Models.HANDHELD);
	}
}
