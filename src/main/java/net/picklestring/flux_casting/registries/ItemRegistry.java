package net.picklestring.flux_casting.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.BottleOScarredStone;
import net.picklestring.flux_casting.items.FluxWand;
import net.picklestring.flux_casting.items.Ring;
import net.picklestring.flux_casting.items.runes.*;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.sql.Ref;

public class ItemRegistry {
	public static final Item EXAMPLE_ITEM = new Item(new QuiltItemSettings());
	public static final Item BLANK_RUNE = new Item(new QuiltItemSettings());
	public static final Item FLUX_BOTTLE = new Item(new QuiltItemSettings());
	public static final Item SCARRED_STONE = new Item(new QuiltItemSettings());
	public static final BottleOScarredStone BOTTLE_O_SCARRED_STONE = new BottleOScarredStone(new QuiltItemSettings());
	public static final Ring RING = new Ring(new QuiltItemSettings());
	public static final FluxWand FLUX_WAND = new FluxWand(new QuiltItemSettings());

	public static final BlockItem RIFT_BENCH = new BlockItem(BlockRegistry.RIFT_BENCH, new QuiltItemSettings());

	public static final ArcaneScripterRune ARCANE_SCRIPTER_RUNE = new ArcaneScripterRune(new QuiltItemSettings());
	public static final RunicConduitRune RUNIC_CONDUIT_RUNE = new RunicConduitRune(new QuiltItemSettings(), RunicConduitRune.Direction.Right, RunicConduitRune.Direction.Left);
	public static final RunicConduitRune RUNIC_CONDUIT_RIGHT_LEFT_RUNE = new RunicConduitRune(new QuiltItemSettings(), RunicConduitRune.Direction.Left, RunicConduitRune.Direction.Right);
	public static final GlyphicInscriptionRune GLYPHIC_INSCRIPTION_RUNE = new GlyphicInscriptionRune(new QuiltItemSettings());
	public static final NumericalImbuementRune NUMERICAL_IMBUEMENT_RUNE = new NumericalImbuementRune(new QuiltItemSettings());
	public static final SoulTrackerRune SOUL_TRACKER_RUNE = new SoulTrackerRune(new QuiltItemSettings());
	public static final NumericalNexusRune NUMERICAL_NEXUS_RUNE = new NumericalNexusRune(new QuiltItemSettings());
	public static final SpatialSliceXRune SPATIAL_SLICE_X_RUNE = new SpatialSliceXRune(new QuiltItemSettings());
	public static final KineticMomentumRune KINETIC_MOMENTUM_RUNE = new KineticMomentumRune(new QuiltItemSettings());
	public static final SummonersEchoRune SUMMONERS_ECHO_RUNE = new SummonersEchoRune(new QuiltItemSettings());
	public static final SightlineSeekerRune SIGHTLINE_SEEKER_RUNE = new SightlineSeekerRune(new QuiltItemSettings());

	public static void Register()
	{
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "example_item"), EXAMPLE_ITEM);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "blank_rune"), BLANK_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "flux_bottle"), FLUX_BOTTLE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "scarred_stone"), SCARRED_STONE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "bottle_o_scarred_stone"), BOTTLE_O_SCARRED_STONE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "rift_bench"), RIFT_BENCH);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "ring"), RING);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "flux_wand"), FLUX_WAND);

		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "arcane_scripter_rune"), ARCANE_SCRIPTER_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "runic_conduit_rune"), RUNIC_CONDUIT_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "glyphic_inscription_rune"), GLYPHIC_INSCRIPTION_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "numerical_imbuement_rune"), NUMERICAL_IMBUEMENT_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "soul_tracker_rune"), SOUL_TRACKER_RUNE);
        Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "numerical_nexus_rune"), NUMERICAL_NEXUS_RUNE);
        Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "spatial_slice_x_rune"), SPATIAL_SLICE_X_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "kinetic_momentum_rune"), KINETIC_MOMENTUM_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "summoners_echo_rune"), SUMMONERS_ECHO_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "sightline_seeker_rune"), SIGHTLINE_SEEKER_RUNE);
		Registry.register(Registries.ITEM, new Identifier(FluxCasting.ModID, "runic_conduit_right_left_rune"), RUNIC_CONDUIT_RIGHT_LEFT_RUNE);
	}
}
