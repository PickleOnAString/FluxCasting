package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class ConstructVectorRune extends RuneItem {
	public ConstructVectorRune(Settings settings) {
		super(
			settings,
			new Type[][] {
				new Type[] {
					Number.class
				},
				new Type[] {
					Number.class
				},
				new Type[] {
					Number.class
				}
			},
			new Type[] {
				Vector3.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/flash_fire_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return;
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		if (!context.isStack) {
			executeInserters(inventory, runeIndex, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, runeIndex, 0);
			stringPartOrStackPop(context, inventory, runeIndex, 1);
			stringPartOrStackPop(context, inventory, runeIndex, 2);
		}

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };
		if (isDataNull(caster, inventory, runeIndex, 1)) return new Object[] { null };
		if (isDataNull(caster, inventory, runeIndex, 2)) return new Object[] { null };

		Number x = loadNumberFromData(0, runeIndex, caster);
		Number y = loadNumberFromData(1, runeIndex, caster);
		Number z = loadNumberFromData(2, runeIndex, caster);

		return new Object[] {
			new Vector3(x.doubleValue(), y.doubleValue(), z.doubleValue())
		};
	}
}
