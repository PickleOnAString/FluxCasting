package net.picklestring.flux_casting.registries;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.Model;
import net.minecraft.data.client.model.Models;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.runes.*;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class RuneRegistry {
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

	public static void GenerateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ARCANE_SCRIPTER_RUNE, Models.SINGLE_LAYER_ITEM);
	}
}
