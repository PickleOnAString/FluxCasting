package net.picklestring.flux_weavers.registries;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.recipes.RiftBenchRecipe;

public class RecipeRegistry {
	public static void Register()
	{
		FluxWeavers.LOGGER.info("Registering recipe");
		Registry.register(Registries.RECIPE_SERIALIZER, RiftBenchRecipe.Serializer.ID, RiftBenchRecipe.Serializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, Identifier.of(FluxWeavers.ModID, RiftBenchRecipe.RiftBenchRecipeType.ID), RiftBenchRecipe.RiftBenchRecipeType.INSTANCE);
	}
}
