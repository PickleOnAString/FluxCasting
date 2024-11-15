package net.picklestring.flux_weavers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.picklestring.flux_weavers.registries.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class InternalizedFluxComponent implements AutoSyncedComponent, ServerTickingComponent {
	private int ticksLastIncrease = 0;
	private int maxValue = 100;
	private int value = maxValue;

	private final PlayerEntity player;

	public InternalizedFluxComponent(PlayerEntity player) {
		this.player = player;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
        this.value = Math.min(value, maxValue);
		this.value = Math.max(this.value, 0);
		ComponentRegistry.INTERNALIZED_FLUX.sync(player);
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int value) {
		this.maxValue = value;
		ComponentRegistry.INTERNALIZED_FLUX.sync(player);
	}

	@Override
	public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup lookup) {
		this.value = tag.getInt("value");
		this.maxValue = tag.getInt("maxValue");
	}

	@Override
	public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup lookup) {
		tag.putInt("value", this.value);
		tag.putInt("maxValue", this.maxValue);
	}

	@Override
	public void serverTick() {
		ticksLastIncrease++;
		if (ticksLastIncrease >= 15)
		{
			if (value < maxValue) {
				this.value = Math.min(this.value + 3, maxValue);
				ComponentRegistry.INTERNALIZED_FLUX.sync(player);
			}
			ticksLastIncrease = 0;
		}
	}
}
