package net.picklestring.flux_weavers.mixin;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.registries.ItemRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public class PlayerMixin {
	@Inject(method="damage", at=@At("TAIL"))
	private void onHit(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		PlayerEntity thisObject = (PlayerEntity)(Object)this;
		FluxWeavers.LOGGER.info("HELP!!! "+thisObject.getUuid());
		Optional<TrinketComponent> otc = TrinketsApi.getTrinketComponent(thisObject);
		if (otc.isPresent()) {
			TrinketComponent tc = otc.get();
			if (tc.isEquipped(ItemRegistry.RING)) {
				tc.getEquipped(ItemRegistry.RING).forEach((Pair<SlotReference, ItemStack> pair) -> {
					FluxWeavers.LOGGER.info(pair.getRight().getName().toString());
				});
				FluxWeavers.LOGGER.info("FLOOOX");
			}
		}
	}
}
