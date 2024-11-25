package net.picklestring.flux_weavers.registries;

import net.minecraft.component.Component;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.items.*;

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
	public static final FluxBow FLUX_BOW = new FluxBow(new Item.Settings()
		.component(ItemComponentRegistry.CAN_BIND_SPELLS, true));
	public static final FluxSword FLUX_SWORD = new FluxSword(FluxToolMaterial.INSTANCE, new Item.Settings()
		.attributeModifiers(SwordItem.createAttributeModifiers(FluxToolMaterial.INSTANCE, 3, -2.4F))
		.component(ItemComponentRegistry.CAN_BIND_SPELLS, true));
	public static final Item ETCHABLE_ALLOY = new Item(new Item.Settings());

	public static final Item SMALL_RAW_FLUX = new Item(new Item.Settings());
	public static final Item MEDIUM_RAW_FLUX = new Item(new Item.Settings());
	public static final Item LARGE_RAW_FLUX = new Item(new Item.Settings());

	public static void Register() {
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "blank_rune"), BLANK_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flux_bottle"), FLUX_BOTTLE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "scarred_stone"), SCARRED_STONE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "bottle_o_scarred_stone"), BOTTLE_O_SCARRED_STONE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "ring"), RING);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flux_wand"), FLUX_WAND);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "spell_holder"), SPELL_HOLDER);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "rune_table"), RUNE_TABLE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flux_bow"), FLUX_BOW);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flux_sword"), FLUX_SWORD);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "etchable_alloy"), ETCHABLE_ALLOY);

		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "small_raw_flux"), SMALL_RAW_FLUX);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "medium_raw_flux"), MEDIUM_RAW_FLUX);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "large_raw_flux"), LARGE_RAW_FLUX);
	}

	public static void GenerateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(BLANK_RUNE, Models.GENERATED);
		itemModelGenerator.register(FLUX_BOTTLE, Models.GENERATED);
		itemModelGenerator.register(SCARRED_STONE, Models.GENERATED);
		itemModelGenerator.register(BOTTLE_O_SCARRED_STONE, Models.GENERATED);
		itemModelGenerator.register(FLUX_WAND, Models.HANDHELD);
		itemModelGenerator.register(ETCHABLE_ALLOY, Models.GENERATED);

		itemModelGenerator.register(SMALL_RAW_FLUX, Models.GENERATED);
		itemModelGenerator.register(MEDIUM_RAW_FLUX, Models.GENERATED);
		itemModelGenerator.register(LARGE_RAW_FLUX, Models.GENERATED);
	}
}
