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

public class NumberAdditionRune extends RuneItem {
	public NumberAdditionRune(Settings settings) {
		super(
			settings,
			new Type[][] {
				new Type[] {
					Number.class
				},
				new Type[] {
					Number.class
				}
			},
			new Type[] {
				Double.class
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
		}

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };
		if (isDataNull(caster, inventory, runeIndex, 1)) return new Object[] { null };

		Number numOne = loadNumberFromData(0, runeIndex, caster);
		Number numTwo = loadNumberFromData(1, runeIndex, caster);

		return new Object[] {
			numOne.doubleValue()+numTwo.doubleValue()
		};
	}
}
