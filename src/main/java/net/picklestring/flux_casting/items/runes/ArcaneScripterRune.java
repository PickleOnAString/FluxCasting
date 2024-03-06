package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArcaneScripterRune extends RuneItem {
	public ArcaneScripterRune(Settings settings) {
		super(settings,
			new Type[][]{ new Type[]{String.class, Number.class} },
			null,
			new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/debug_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, index, caster, pos, world);

		String debugString;
		if (data[0] == null) {
			debugString = getStringPartOrDefault(1, "", inventory.get(index));
		}
		else {
			if (data[0] instanceof Number) {
				debugString = getDataOrDefault(0, 0d, Number.class).toString();
			} else {
				debugString = getDataOrDefault(0, getStringPartOrDefault(1, "", inventory.get(index)), String.class);
			}
		}

		caster.sendMessage(Text.literal(debugString), false);

		data = new Object[dataFormat.length];
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		return null;
	}
}
