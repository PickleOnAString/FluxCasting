package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.lang.reflect.Type;

public class StringInputRune extends RuneItem {

	public StringInputRune(Settings settings) {
		super(settings, new Type[][]{ new Type[]{String.class} }, new Type[]{String.class});
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int outputIndex, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		return getStringPartOrDefault(1, "", inventory.get(runeIndex));
	}
}
