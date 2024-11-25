package net.picklestring.flux_weavers.items;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.picklestring.flux_weavers.registries.ItemRegistry;

public class FluxToolMaterial implements ToolMaterial {
	public static final FluxToolMaterial INSTANCE = new FluxToolMaterial();

	@Override
	public int getDurability() {
		return 1561;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 8;
	}

	@Override
	public float getAttackDamage() {
		return 3;
	}

	@Override
	public TagKey<Block> getInverseTag() {
		return null;
	}

	@Override
	public int getEnchantability() {
		return 30;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ItemRegistry.LARGE_RAW_FLUX);
	}
}
