package net.picklestring.flux_casting.registries;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.recipes.RiftBenchRecipe;

public class RecipeRegistry {
	public static void Register()
	{
		FluxCasting.LOGGER.info("Registering recipe");
		Registry.register(Registries.RECIPE_SERIALIZER, RiftBenchRecipe.Serializer.ID, RiftBenchRecipe.Serializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, Identifier.of(FluxCasting.ModID, RiftBenchRecipe.RiftBenchRecipeType.ID), RiftBenchRecipe.RiftBenchRecipeType.INSTANCE);
	}
}
