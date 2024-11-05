package net.picklestring.flux_casting.registries;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.picklestring.flux_casting.FluxCasting;

public class ItemPredicateRegistry {
	public static void Register() {
		ModelPredicateProviderRegistry.register(ItemRegistry.SPELL_HOLDER, Identifier.of(FluxCasting.ModID, "spell"), (itemStack, clientWorld, livingEntity, seed) ->
			itemStack.contains(ItemComponentRegistry.SPELL) ? 1 : 0);
	}
}
