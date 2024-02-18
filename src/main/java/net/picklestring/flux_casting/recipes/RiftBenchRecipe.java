package net.picklestring.flux_casting.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.ImplementedInventory;

public class RiftBenchRecipe implements Recipe<ImplementedInventory> {
	public DefaultedList<Ingredient> ingredients =  DefaultedList.ofSize(10, Ingredient.EMPTY);
	public final ItemStack outputStack;
	private final Identifier id;

	public RiftBenchRecipe(DefaultedList<Ingredient> ingredients, ItemStack outputStack, Identifier id) {
		this.ingredients = ingredients;
		this.outputStack = outputStack;
		this.id = id;
	}
	@Override
	public boolean matches(ImplementedInventory inventory, World world) {
		FluxCasting.LOGGER.info("FUCK THIS SHIT");
		if (inventory.size() < 10) return false;
		for (int i = 0; 1 < ingredients.size(); i++)
		{
			if (!ingredients.get(i).test(inventory.getStack(i))) {
                return false;
            }
		}
		return true;
	}

	@Override
	public ItemStack craft(ImplementedInventory inventory, DynamicRegistryManager registryManager) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean fits(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getResult(DynamicRegistryManager registryManager) {
		return outputStack;
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RiftBenchRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return RiftBenchRecipeType.INSTANCE;
	}

	public static class RiftBenchRecipeType implements RecipeType<RiftBenchRecipe> {
		// Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
		private RiftBenchRecipeType() {}
		public static final RiftBenchRecipeType INSTANCE = new RiftBenchRecipeType();

		// This will be needed in step 4
		public static final String ID = "rift_bench_recipe";
	}
}

