package net.picklestring.flux_casting.items.runes;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class RuneItem extends Item {
	public ArrayList<Object> data = new ArrayList<>();

	public RuneItem(Settings settings) {
		super(settings);
	}

	public abstract void onCast(DefaultedList<ItemStack> inventory, int index);
	public abstract Object getValue(DefaultedList<ItemStack> inventory, int index);

	public void executeInserters(DefaultedList<ItemStack> inventory, int index)
	{
		FluxCasting.LOGGER.info("executing inserters from: "+index+", rune: "+this.getClass().getName());
		for (int i = 0; i < 4; i++)
		{
			FluxCasting.LOGGER.info("RuneItem: executeInserters");
			if (InserterRune.getAdjacentIndexFromDirection(index, i) >= 0 && InserterRune.getAdjacentIndexFromDirection(index, i) < inventory.size()) {
				FluxCasting.LOGGER.info("RuneItem: executeInserters: check index inbounds");
				ItemStack newItem = inventory.get(InserterRune.getAdjacentIndexFromDirection(index, i));
				if (newItem.getItem() instanceof InserterRune) {
					FluxCasting.LOGGER.info("RuneItem: executeInserters: check index inbounds: check if inserter");
					if (((InserterRune) newItem.getItem()).insertDirection == InserterRune.invertDirection(InserterRune.intToDirection(i))) {
						FluxCasting.LOGGER.info("RuneItem: executeInserters: check index inbounds: check if inserter: check if insert dir is facing rune");
						((InserterRune) newItem.getItem()).getValue(inventory, InserterRune.getAdjacentIndexFromDirection(index, InserterRune.intToDirection(i)));
					}
				}
			}
		}
	}

	public String getStringPartOrDefault(int index, String defaultString, ItemStack itemStack)
	{
		String name = itemStack.getName().getString();
		String[] strings = name.split(" ?: ?");
		if (index >= strings.length) return defaultString;
		return strings[index];
	}

	public <T> T getDataOrDefault(int index, T defaultData, Class<T> clazz) {
		FluxCasting.LOGGER.info("index: "+index+", data_size: "+data.size());
		if (index >= data.size()) {
			data.add(index, defaultData);
			FluxCasting.LOGGER.info("Setting default value: 0");
		}else if (data.get(index) == null) {
			data.set(index, defaultData);
			FluxCasting.LOGGER.info("Setting default value: 1");
		} else if (!clazz.isInstance(data.get(index))) {
			data.set(index, defaultData);
			FluxCasting.LOGGER.info("Setting default value: 2");
		}

		FluxCasting.LOGGER.info("getting value");
		return clazz.cast(data.get(index));
	}
}
