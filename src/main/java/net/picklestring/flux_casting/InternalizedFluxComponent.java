package net.picklestring.flux_casting;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ClientTickingComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.nbt.NbtCompound;
import net.picklestring.flux_casting.registries.ComponentRegistry;

public class InternalizedFluxComponent implements PlayerComponent, AutoSyncedComponent, ServerTickingComponent {
	private int ticksLastIncrease = 0;
	private int maxValue = 100;
	private int value = maxValue;

	private Object provider;

	public InternalizedFluxComponent(Object provider) {
		this.provider = provider;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
        this.value = Math.min(value, maxValue);
		this.value = Math.max(this.value, 0);
		ComponentRegistry.INTERNALIZED_FLUX.sync(provider);
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int value) {
		this.maxValue = value;
		ComponentRegistry.INTERNALIZED_FLUX.sync(provider);
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		this.value = tag.getInt("value");
		this.maxValue = tag.getInt("maxValue");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
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
				ComponentRegistry.INTERNALIZED_FLUX.sync(provider);
			}
			ticksLastIncrease = 0;
		}
	}
}
