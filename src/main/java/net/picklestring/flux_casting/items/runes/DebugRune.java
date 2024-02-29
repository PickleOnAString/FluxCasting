package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DebugRune extends RuneItem {
	public DebugRune(Settings settings) {
		super(settings, new Type[][]{ new Type[]{String.class, Number.class} }, new Type[]{});
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, index, caster, pos, world);
		String debugString;
		if (data.isEmpty()) {
			debugString = getStringPartOrDefault(1, "", inventory.get(index));
		}
		else {
			if (data.get(0) instanceof Number) {
				debugString = getDataOrDefault(0, Double.parseDouble(getStringPartOrDefault(1, "0", inventory.get(index))), Number.class).toString();
			} else {
				debugString = getDataOrDefault(0, getStringPartOrDefault(1, "", inventory.get(index)), String.class);
			}
		}
		FluxCasting.LOGGER.info(debugString);
		data = new ArrayList<>();
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int outputIndex, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		return null;
	}
}
