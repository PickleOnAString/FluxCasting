package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SpatialSliceXRune extends RuneItem {
	public SpatialSliceXRune(Settings settings) {
		super(settings,
			new Type[][]{ new Type[]{Vector3.class} },
			Double.class,
			new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/extract_x_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, runeIndex, caster, pos, world);

		Double x = getDataOrDefault(0, new Vector3(0, 0, 0), Vector3.class).getX();

		data = new Object[dataFormat.length];
		return x;
	}
}
