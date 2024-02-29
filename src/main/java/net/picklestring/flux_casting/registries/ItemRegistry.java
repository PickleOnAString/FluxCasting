package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.BottleOScarredStone;
import net.picklestring.flux_casting.items.runes.*;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ItemRegistry {
	public static final Item EXAMPLE_ITEM = new Item(new QuiltItemSettings());
	public static final Item BLANK_RUNE = new Item(new QuiltItemSettings());
	public static final Item FLUX_BOTTLE = new Item(new QuiltItemSettings());
	public static final Item SCARRED_STONE = new Item(new QuiltItemSettings());
	public static final BottleOScarredStone BOTTLE_O_SCARRED_STONE = new BottleOScarredStone(new QuiltItemSettings());
	public static final BlockItem RIFT_BENCH = new BlockItem(BlockRegistry.RIFT_BENCH, new FabricItemSettings());
	public static final DebugRune DEBUG_RUNE = new DebugRune(new QuiltItemSettings());
	public static final InserterRune LEFT_TO_RIGHT_INSERTER_RUNE = new InserterRune(new QuiltItemSettings(), InserterRune.Direction.Right, InserterRune.Direction.Left);
	public static final StringInputRune STRING_INPUT_RUNE = new StringInputRune(new QuiltItemSettings());
	public static final IntInputRune INT_INPUT_RUNE = new IntInputRune(new QuiltItemSettings());
	public static final GetPositionRune GET_POSITION_RUNE = new GetPositionRune(new QuiltItemSettings());
	public static final IntToStringRune INT_TO_STRING_RUNE = new IntToStringRune(new QuiltItemSettings());
	public static final GetXRune GET_X_RUNE = new GetXRune(new QuiltItemSettings());
	public static final SplitVector3Rune SPLIT_VECTOR_3_RUNE = new SplitVector3Rune(new QuiltItemSettings());

	public static void Register()
	{
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "example_item"), EXAMPLE_ITEM);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "blank_rune"), BLANK_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "flux_bottle"), FLUX_BOTTLE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "scarred_stone"), SCARRED_STONE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "bottle_o_scarred_stone"), BOTTLE_O_SCARRED_STONE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "debug_rune"), DEBUG_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "left_to_right_inserter_rune"), LEFT_TO_RIGHT_INSERTER_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "string_input_rune"), STRING_INPUT_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "int_input_rune"), INT_INPUT_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "get_position_rune"), GET_POSITION_RUNE);
        Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "int_to_string_rune"), INT_TO_STRING_RUNE);
        Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "get_x_rune"), GET_X_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "split_vector_rune"), SPLIT_VECTOR_3_RUNE);
	}
}
