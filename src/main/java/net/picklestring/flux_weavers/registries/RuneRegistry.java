package net.picklestring.flux_weavers.registries;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.items.runes.*;

import java.util.Optional;

public class RuneRegistry {
	public static final ArcaneScripterRune ARCANE_SCRIPTER_RUNE = new ArcaneScripterRune(new Item.Settings());
	public static final KineticMomentumRune KINETIC_MOMENTUM_RUNE = new KineticMomentumRune(new Item.Settings());
	public static final RendStoneRune REND_STONE_RUNE = new RendStoneRune(new Item.Settings());
	public static final FluxStackerRune FLUX_STACKER_RUNE = new FluxStackerRune(new Item.Settings());
	public static final FlashFireRune FLASH_FIRE_RUNE = new FlashFireRune(new Item.Settings());
	public static final VerdantBloomRune VERDANT_BLOOM_RUNE = new VerdantBloomRune(new Item.Settings());

	public static final RunicConduitRune RUNIC_CONDUIT_RUNE = new RunicConduitRune(new Item.Settings(), RunicConduitRune.Direction.Right, RunicConduitRune.Direction.Left);
	public static final RunicConduitRune RUNIC_CONDUIT_RIGHT_LEFT_RUNE = new RunicConduitRune(new Item.Settings(), RunicConduitRune.Direction.Left, RunicConduitRune.Direction.Right);
	public static final RunicConduitRune RUNIC_CONDUIT_UP_DOWN_RUNE = new RunicConduitRune(new Item.Settings(), RunicConduitRune.Direction.Down, RunicConduitRune.Direction.Up);

	public static final GlyphicInscriptionRune GLYPHIC_INSCRIPTION_RUNE = new GlyphicInscriptionRune(new Item.Settings());
	public static final NumericalImbuementRune NUMERICAL_IMBUEMENT_RUNE = new NumericalImbuementRune(new Item.Settings());
	public static final SoulTrackerRune SOUL_TRACKER_RUNE = new SoulTrackerRune(new Item.Settings());
	public static final GazeAnchorRune GAZE_ANCHOR_RUNE = new GazeAnchorRune(new Item.Settings());
	public static final NumericalNexusRune NUMERICAL_NEXUS_RUNE = new NumericalNexusRune(new Item.Settings());
	public static final SpatialSliceRune SPATIAL_SLICE_X_RUNE = new SpatialSliceRune(new Item.Settings());
	public static final SummonersEchoRune SUMMONERS_ECHO_RUNE = new SummonersEchoRune(new Item.Settings());
	public static final SightlineSeekerRune SIGHTLINE_SEEKER_RUNE = new SightlineSeekerRune(new Item.Settings());
	public static final TargetPerceptionRune TARGET_PERCEPTION_RUNE = new TargetPerceptionRune(new Item.Settings());
	public static final ProjectileDestinationRune PROJECTILE_DESTINATION_RUNE = new ProjectileDestinationRune(new Item.Settings());
	public static final TargetTrackerRune TARGET_TRACKER_RUNE = new TargetTrackerRune(new Item.Settings());
	public static final FluxValidatorRune FLUX_VALIDATOR_RUNE = new FluxValidatorRune(new Item.Settings());
	public static final ProjectilePointerRune PROJECTILE_POINTER_RUNE = new ProjectilePointerRune(new Item.Settings());

	public static final Identifier ACTION_RUNE_TEMPLATE = Identifier.of(FluxWeavers.ModID, "item/template_action_rune");
	public static final Identifier FLOW_RUNE_TEMPLATE = Identifier.of(FluxWeavers.ModID, "item/template_flow_rune");
	public static final Identifier DATA_RUNE_TEMPLATE = Identifier.of(FluxWeavers.ModID, "item/template_data_rune");

	public static void Register()
	{
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "arcane_scripter_rune"), ARCANE_SCRIPTER_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "runic_conduit_rune"), RUNIC_CONDUIT_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "glyphic_inscription_rune"), GLYPHIC_INSCRIPTION_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "numerical_imbuement_rune"), NUMERICAL_IMBUEMENT_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "soul_tracker_rune"), SOUL_TRACKER_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "gaze_anchor_rune"), GAZE_ANCHOR_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "numerical_nexus_rune"), NUMERICAL_NEXUS_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "spatial_slice_x_rune"), SPATIAL_SLICE_X_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "kinetic_momentum_rune"), KINETIC_MOMENTUM_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "summoners_echo_rune"), SUMMONERS_ECHO_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "sightline_seeker_rune"), SIGHTLINE_SEEKER_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "runic_conduit_right_left_rune"), RUNIC_CONDUIT_RIGHT_LEFT_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "target_perception_rune"), TARGET_PERCEPTION_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "rend_stone_rune"), REND_STONE_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "runic_conduit_up_down_rune"), RUNIC_CONDUIT_UP_DOWN_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flux_stacker_rune"), FLUX_STACKER_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flash_fire_rune"), FLASH_FIRE_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "verdant_bloom_rune"), VERDANT_BLOOM_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "projectile_destination_rune"), PROJECTILE_DESTINATION_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "target_tracker_rune"), TARGET_TRACKER_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "flux_validator_rune"), FLUX_VALIDATOR_RUNE);
		Registry.register(Registries.ITEM, Identifier.of(FluxWeavers.ModID, "projectile_pointer_rune"), PROJECTILE_POINTER_RUNE);
	}

	public static void GenerateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ARCANE_SCRIPTER_RUNE, new Model(Optional.of(ACTION_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(KINETIC_MOMENTUM_RUNE, new Model(Optional.of(ACTION_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(REND_STONE_RUNE, new Model(Optional.of(ACTION_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(FLUX_STACKER_RUNE, new Model(Optional.of(ACTION_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(FLASH_FIRE_RUNE, new Model(Optional.of(ACTION_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(VERDANT_BLOOM_RUNE, new Model(Optional.of(ACTION_RUNE_TEMPLATE), Optional.empty()));

		itemModelGenerator.register(RUNIC_CONDUIT_RUNE, new Model(Optional.of(FLOW_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(RUNIC_CONDUIT_RIGHT_LEFT_RUNE, new Model(Optional.of(FLOW_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(RUNIC_CONDUIT_UP_DOWN_RUNE, new Model(Optional.of(FLOW_RUNE_TEMPLATE), Optional.empty()));

		itemModelGenerator.register(GLYPHIC_INSCRIPTION_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(NUMERICAL_IMBUEMENT_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(SOUL_TRACKER_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(GAZE_ANCHOR_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(NUMERICAL_NEXUS_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(SPATIAL_SLICE_X_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(SUMMONERS_ECHO_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(SIGHTLINE_SEEKER_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(TARGET_PERCEPTION_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(TARGET_TRACKER_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(PROJECTILE_DESTINATION_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(FLUX_VALIDATOR_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
		itemModelGenerator.register(PROJECTILE_POINTER_RUNE, new Model(Optional.of(DATA_RUNE_TEMPLATE), Optional.empty()));
	}
}
