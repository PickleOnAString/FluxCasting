package net.picklestring.flux_casting.mixin;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.registries.ItemRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public class PlayerMixin {
	@Inject(method="damage", at=@At("TAIL"))
	private void onHit(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		PlayerEntity thisObject = (PlayerEntity)(Object)this;
		FluxCasting.LOGGER.info("HELP!!! "+thisObject.getUuid());
		Optional<TrinketComponent> otc = TrinketsApi.getTrinketComponent(thisObject);
		if (otc.isPresent()) {
			TrinketComponent tc = otc.get();
			if (tc.isEquipped(ItemRegistry.RING)) {
				tc.getEquipped(ItemRegistry.RING).forEach((Pair<SlotReference, ItemStack> pair) -> {
					FluxCasting.LOGGER.info(pair.getRight().getName().toString());
				});
				FluxCasting.LOGGER.info("FLOOOX");
			}
		}
	}

	@Inject(method="writeCustomDataToNbt", at=@At("TAIL"))
	private void writeCustomData(NbtCompound nbt, CallbackInfo ci)
	{
		nbt.p
	}
}
