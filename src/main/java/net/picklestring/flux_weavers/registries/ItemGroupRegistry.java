package net.picklestring.flux_weavers.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;

public class ItemGroupRegistry {
	private static final ItemGroup MISC_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(ItemRegistry.BLANK_RUNE))
		.displayName(Text.translatable("itemGroup.flux_weavers.misc_group"))
		.entries((context, entries) -> {
			entries.add(ItemRegistry.BLANK_RUNE);
			entries.add(ItemRegistry.FLUX_BOTTLE);
			entries.add(ItemRegistry.BOTTLE_O_SCARRED_STONE);
			entries.add(ItemRegistry.SCARRED_STONE);
			entries.add(ItemRegistry.RIFT_BENCH);
			entries.add(ItemRegistry.SMALL_RAW_FLUX);
			entries.add(ItemRegistry.MEDIUM_RAW_FLUX);
			entries.add(ItemRegistry.LARGE_RAW_FLUX);
			entries.add(ItemRegistry.ETCHABLE_ALLOY);
			entries.add(ItemRegistry.FLUX_BOW);
			entries.add(ItemRegistry.FLUX_SWORD);
			entries.add(ItemRegistry.SPELL_HOLDER);
			entries.add(ItemRegistry.FLUX_WAND);
		})
		.build();

	private static final ItemGroup RUNE_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(RuneRegistry.FLUX_STACKER_RUNE))
		.displayName(Text.translatable("itemGroup.flux_weavers.rune_group"))
		.entries((context, entries) -> {
			entries.add(RuneRegistry.ARCANE_SCRIPTER_RUNE);
			entries.add(RuneRegistry.KINETIC_MOMENTUM_RUNE);
			entries.add(RuneRegistry.REND_STONE_RUNE);
			entries.add(RuneRegistry.FLUX_STACKER_RUNE);
			entries.add(RuneRegistry.FLASH_FIRE_RUNE);
			entries.add(RuneRegistry.VERDANT_BLOOM_RUNE);
			entries.add(RuneRegistry.TELEPORTATION_RUNE);

			entries.add(RuneRegistry.RUNIC_CONDUIT_RUNE);
			entries.add(RuneRegistry.RUNIC_CONDUIT_RIGHT_LEFT_RUNE);
			entries.add(RuneRegistry.RUNIC_CONDUIT_UP_DOWN_RUNE);
			entries.add(RuneRegistry.TIME_REVERSAL_RUNE);

			entries.add(RuneRegistry.LESSER_NUMBER_COMPARISON_RUNE);

			entries.add(RuneRegistry.GLYPHIC_INSCRIPTION_RUNE);
			entries.add(RuneRegistry.NUMERICAL_IMBUEMENT_RUNE);
			entries.add(RuneRegistry.SOUL_TRACKER_RUNE);
			entries.add(RuneRegistry.GAZE_ANCHOR_RUNE);
			entries.add(RuneRegistry.NUMERICAL_NEXUS_RUNE);
			entries.add(RuneRegistry.SPATIAL_SLICE_X_RUNE);
			entries.add(RuneRegistry.SUMMONERS_ECHO_RUNE);
			entries.add(RuneRegistry.SIGHTLINE_SEEKER_RUNE);
			entries.add(RuneRegistry.TARGET_PERCEPTION_RUNE);
			entries.add(RuneRegistry.TARGET_TRACKER_RUNE);
			entries.add(RuneRegistry.PROJECTILE_DESTINATION_RUNE);
			entries.add(RuneRegistry.PROJECTILE_POINTER_RUNE);
			entries.add(RuneRegistry.AXIS_INSPECTOR_X_RUNE);
			entries.add(RuneRegistry.AXIS_INSPECTOR_Y_RUNE);
			entries.add(RuneRegistry.AXIS_INSPECTOR_Z_RUNE);
			entries.add(RuneRegistry.VECTOR_MULTIPLICATION_RUNE);
			entries.add(RuneRegistry.VECTOR_ADDITION_RUNE);
			entries.add(RuneRegistry.FACE_FINDER_RUNE);
			entries.add(RuneRegistry.NUMBER_ADDITION_RUNE);
			entries.add(RuneRegistry.POINTER_MULTIPLICATION_RUNE);
			entries.add(RuneRegistry.CONSTRUCT_VECTOR_RUNE);
		})
		.build();

	public static void Register()
	{
		Registry.register(Registries.ITEM_GROUP, Identifier.of(FluxWeavers.ModID, "misc_group"), MISC_GROUP);
		Registry.register(Registries.ITEM_GROUP, Identifier.of(FluxWeavers.ModID, "rune_group"), RUNE_GROUP);
	}
}
