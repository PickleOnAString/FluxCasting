package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.lang.reflect.Type;

public class IntInputRune extends RuneItem {
	public IntInputRune(Settings settings) {
		super(settings, new Type[][]{ new Type[]{Integer.class} }, new Type[]{Integer.class});
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int outputIndex, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		return Integer.parseInt(getStringPartOrDefault(1, "0", inventory.get(runeIndex)));
	}
}
