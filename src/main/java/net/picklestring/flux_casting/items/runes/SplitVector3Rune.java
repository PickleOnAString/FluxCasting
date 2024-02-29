package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SplitVector3Rune extends RuneItem{
	public SplitVector3Rune(Settings settings) {
		super(settings, new Type[][]{new Type[]{Vector3.class}}, new Type[]{Double.class, Double.class, Double.class});
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int outputIndex, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, runeIndex, caster, pos, world);
		Vector3 vec = getDataOrDefault(0, new Vector3(0, 0, 0), Vector3.class);
		data = new ArrayList<>();

		if (outputIndex == 0)
		{
			return vec.getX();
		}
		else if (outputIndex == 1)
		{
			return vec.getY();
		}
		else if (outputIndex == 2) {
            return vec.getZ();
        }
		return 0d;
	}
}
