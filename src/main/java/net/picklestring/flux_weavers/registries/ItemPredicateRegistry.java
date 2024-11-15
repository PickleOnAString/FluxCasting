package net.picklestring.flux_weavers.registries;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_weavers.FluxWeavers;

public class ItemPredicateRegistry {
	public static void Register() {
		//Spell holder
		ModelPredicateProviderRegistry.register(ItemRegistry.SPELL_HOLDER, Identifier.of(FluxWeavers.ModID, "spell"), (itemStack, clientWorld, livingEntity, seed) ->
			itemStack.contains(ItemComponentRegistry.SPELL) ? 1 : 0);

		//Flux bow
		ModelPredicateProviderRegistry.register(ItemRegistry.FLUX_BOW, Identifier.ofVanilla("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
			if (livingEntity == null) {
				return 0.0F;
			}
			return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime(livingEntity) - livingEntity.getItemUseTimeLeft()) / 20.0F;
		});
		ModelPredicateProviderRegistry.register(ItemRegistry.FLUX_BOW, Identifier.ofVanilla("pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
			if (livingEntity == null) {
				return 0.0F;
			}
			return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
		});
		ModelPredicateProviderRegistry.register(ItemRegistry.FLUX_BOW, Identifier.of(FluxWeavers.ModID, "spell"), (itemStack, clientWorld, livingEntity, seed) ->
			itemStack.contains(ItemComponentRegistry.SPELL) ? 1 : 0);
	}
}
