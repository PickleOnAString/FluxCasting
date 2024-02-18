package net.picklestring.flux_casting.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.picklestring.flux_casting.FluxCasting;

public class RiftBenchRecipeSerializer implements RecipeSerializer<RiftBenchRecipe> {
	public static final RiftBenchRecipeSerializer INSTANCE = new RiftBenchRecipeSerializer();

	// This will be the "type" field in the json
	public static final Identifier ID = new Identifier(FluxCasting.ModID+"rift_bench_recipe");

	private RiftBenchRecipeSerializer() {
	}

	@Override
	// Turns json into Recipe
	public RiftBenchRecipe read(Identifier id, JsonObject json) {
		FluxCasting.LOGGER.info("Loading custom recipe");
		if (!json.has("ingredients")) throw new JsonSyntaxException("Missing ingredients, expected to find a JsonArray");
		if (!json.get("ingredients").isJsonArray()) throw new JsonSyntaxException("ingredients should be a JsonArray");
		if (!json.has("result")) throw new JsonSyntaxException("Missing result, expected to find a string");
		if (!json.has("resultAmount")) throw new JsonSyntaxException("Missing resultAmount, expected to find a number");

		RiftBenchRecipeJsonFormat recipeJson = new Gson().fromJson(json, RiftBenchRecipeJsonFormat.class);
		DefaultedList<Ingredient> ingredients = DefaultedList.of();
        for (JsonElement element : recipeJson.ingredients) {
            Ingredient ingredient = Ingredient.fromJson(element);
            ingredients.add(ingredient);
        }
        ItemStack outputStack = new ItemStack(Registries.ITEM.get(new Identifier(recipeJson.result)), recipeJson.resultAmount);
		return new RiftBenchRecipe(ingredients, outputStack, id);
	}
	@Override
	// Turns Recipe into PacketByteBuf
	public void write(PacketByteBuf packetData, RiftBenchRecipe recipe) {
		for (int i = 0; i < 10; i++)
		{
			recipe.ingredients.get(i).write(packetData);
		}
		packetData.writeItemStack(recipe.outputStack);
	}

	@Override
	// Turns PacketByteBuf into Recipe
	public RiftBenchRecipe read(Identifier id, PacketByteBuf packetData) {
		DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(10, Ingredient.EMPTY);
		for (int i = 0; i < 10; i++)
		{
			ingredients.set(i, Ingredient.fromPacket(packetData));
		}
		ItemStack out = packetData.readItemStack();
		return new RiftBenchRecipe(ingredients, out, id);
	}
}
