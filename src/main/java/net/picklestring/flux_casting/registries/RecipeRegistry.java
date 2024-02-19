package net.picklestring.flux_casting.registries;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.recipes.RiftBenchRecipe;
import net.picklestring.flux_casting.recipes.RiftBenchRecipeSerializer;

public class RecipeRegistry {
	public static void Register()
	{
		FluxCasting.LOGGER.info("Registering recipe");
		Registry.register(Registries.RECIPE_SERIALIZER, RiftBenchRecipeSerializer.ID, RiftBenchRecipeSerializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, new Identifier(FluxCasting.ModID, RiftBenchRecipe.RiftBenchRecipeType.ID), RiftBenchRecipe.RiftBenchRecipeType.INSTANCE);
	}
}
