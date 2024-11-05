package net.picklestring.flux_casting.recipes;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.ImplementedInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RiftBenchRecipe implements Recipe<CraftingRecipeInput> {
	public List<Ingredient> ingredients =  DefaultedList.ofSize(10, Ingredient.EMPTY);
	public final ItemStack outputStack;

	public RiftBenchRecipe(List<Ingredient> ingredients, ItemStack outputStack) {
		this.ingredients = ingredients;
		this.outputStack = outputStack;
	}
	@Override
	public boolean matches(CraftingRecipeInput inventory, World world) {
		if (inventory.getSize() < 10) return false;
		for (int i = 0; i < ingredients.size(); i++)
		{
			if (!ingredients.get(i).test(inventory.getStackInSlot(i))) {
                return false;
            }
		}
		return true;
	}

	@Override
	public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean fits(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
		return outputStack;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return RiftBenchRecipeType.INSTANCE;
	}

	@Override
	public DefaultedList<Ingredient> getIngredients() {
		DefaultedList<Ingredient> list = DefaultedList.ofSize(this.ingredients.size());
		list.addAll(ingredients);
		return list;
	}

	public static class RiftBenchRecipeType implements RecipeType<RiftBenchRecipe> {
		// Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
		private RiftBenchRecipeType() {}
		public static final RiftBenchRecipeType INSTANCE = new RiftBenchRecipeType();

		// This will be needed in step 4
		public static final String ID = "rift_bench_recipe";
	}

	public static class Serializer implements RecipeSerializer<RiftBenchRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String ID = "gem_polishing";
		public static final MapCodec<RiftBenchRecipe> CODEC = RecordCodecBuilder.mapCodec(in -> in.group(
			Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(RiftBenchRecipe::getIngredients),
			ItemStack.CODEC.fieldOf("output").forGetter(r -> r.outputStack)
		).apply(in, RiftBenchRecipe::new));

		public static final PacketCodec<RegistryByteBuf, RiftBenchRecipe> STREAM = PacketCodec.ofStatic(Serializer::write, Serializer::read);

		@Override
		public MapCodec<RiftBenchRecipe> codec() {
			return CODEC;
		}

		@Override
		public PacketCodec<RegistryByteBuf, RiftBenchRecipe> packetCodec() {
			return STREAM;
		}

		private static RiftBenchRecipe read(RegistryByteBuf buf) {
			ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
			DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(10, Ingredient.EMPTY);
			for (int i = 0; i < 10; i++) {
				ingredients.set(i, Ingredient.PACKET_CODEC.decode(buf));
			}
			return new RiftBenchRecipe(ingredients, output);
		}

		private static void write(RegistryByteBuf buf, RiftBenchRecipe recipe) {
			ItemStack.PACKET_CODEC.encode(buf, recipe.outputStack);
			for (int i = 0; i < 10; i++)
			{
				Ingredient.PACKET_CODEC.encode(buf, recipe.ingredients.get(i));
			}
		}
	}
}

