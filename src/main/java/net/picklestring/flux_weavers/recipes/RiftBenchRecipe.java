package net.picklestring.flux_weavers.recipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;

import java.util.Arrays;
import java.util.List;

public class RiftBenchRecipe implements Recipe<CraftingRecipeInput> {
	public List<Ingredient> ingredients =  DefaultedList.ofSize(10, Ingredient.EMPTY);
	public final ItemStack outputStack;

	public RiftBenchRecipe(List<Ingredient> ingredients, ItemStack outputStack) {
		this.ingredients = ingredients;
		this.outputStack = outputStack;
	}
	@Override
	public boolean matches(CraftingRecipeInput inventory, World world) {
		FluxWeavers.LOGGER.info("TEST 2");
		if (inventory.getSize() < 10) return false;
		FluxWeavers.LOGGER.info("TEST 3");
		for (int i = 0; i < ingredients.size(); i++)
		{
			FluxWeavers.LOGGER.info("TEST 4 "+i);
			FluxWeavers.LOGGER.info(Arrays.toString(ingredients.get(i).getMatchingStacks()));
			FluxWeavers.LOGGER.info(inventory.getStackInSlot(i).toString());
			for (RegistryEntry<Item> item : Registries.ITEM.getOrCreateEntryList(TagKey.of(RegistryKeys.ITEM, Identifier.of(FluxWeavers.ModID, "flux_small")))) {
				FluxWeavers.LOGGER.info("TAG: "+String.valueOf(item.value().getName()));
			}

			if (!ingredients.get(i).test(inventory.getStackInSlot(i))) {
                return false;
            }
		}
		FluxWeavers.LOGGER.info("TEST 5");
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
		public static final String ID = "rift_bench_recipe";
		public static final MapCodec<RiftBenchRecipe> CODEC = RecordCodecBuilder.mapCodec(in -> in.group(
			Ingredient.ALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(RiftBenchRecipe::getIngredients),
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

