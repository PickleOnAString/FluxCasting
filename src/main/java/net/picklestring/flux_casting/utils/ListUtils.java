package net.picklestring.flux_casting.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	public static <E> DefaultedList<E> listToDefaultedList(List<E> list, E defaultItem) {
		DefaultedList<E> defaulted = DefaultedList.ofSize(list.size(), defaultItem);
		for (int i = 0; i< list.size(); i++) {
			defaulted.set(i, list.get(i));
		}
		return defaulted;
	}

	public static List<ItemStack> cloneItemStackList(List<ItemStack> list) {
		List<ItemStack> newList = new ArrayList<>();
        for (ItemStack itemStack : list) {
            newList.add(itemStack.copy());
        }
		return newList;
	}
}
