package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NumericalNexusRune extends RuneItem {
	public NumericalNexusRune(Settings settings) {
		super(settings,
			new Type[][]{ new Type[]{Number.class} },
			String.class,
			new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/debug_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, runeIndex, caster, pos, world);

		String string = getDataOrDefault(0, getDoubleFromName(1, inventory.get(runeIndex), 0d), Number.class).toString();

		data = new Object[dataFormat.length];
		return string;
	}
}
