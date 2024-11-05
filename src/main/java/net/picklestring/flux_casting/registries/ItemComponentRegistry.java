package net.picklestring.flux_casting.registries;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;

import java.util.List;

public class ItemComponentRegistry {
	public static final ComponentType<Integer> FLUX = Registry.register(
		Registries.DATA_COMPONENT_TYPE,
		Identifier.of(FluxCasting.ModID, "flux"),
		ComponentType.<Integer>builder().codec(Codec.INT).build()
	);
	public static final ComponentType<Float> FLUX_AVAILABILITY = Registry.register(
		Registries.DATA_COMPONENT_TYPE,
		Identifier.of(FluxCasting.ModID, "flux_availability"),
		ComponentType.<Float>builder().codec(Codec.FLOAT).build()
	);
	public static final ComponentType<Boolean> CAN_CONSUME_FLUX = Registry.register(
		Registries.DATA_COMPONENT_TYPE,
		Identifier.of(FluxCasting.ModID, "can_consume_flux"),
		ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
	);
	public static final ComponentType<Boolean> CAN_BIND_SPELLS = Registry.register(
		Registries.DATA_COMPONENT_TYPE,
		Identifier.of(FluxCasting.ModID, "can_bind_spells"),
		ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
	);
	public static final ComponentType<List<ItemStack>> SPELL = Registry.register(
		Registries.DATA_COMPONENT_TYPE,
		Identifier.of(FluxCasting.ModID, "spell"),
		ComponentType.<List<ItemStack>>builder().codec(ItemStack.OPTIONAL_CODEC.listOf()).build()
	);
	public static void Register() {

	}
}
