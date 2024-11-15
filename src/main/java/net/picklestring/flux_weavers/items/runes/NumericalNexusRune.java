package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;

import java.lang.reflect.Type;

public class NumericalNexusRune extends RuneItem {
	public NumericalNexusRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{Number.class}
			},
			new Type[]{
				String.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/numerical_nexus_rune_overlay.png")
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

		String str =  null;

		if (isDataNull(caster, inventory, runeIndex, 0)) return new Object[] { null };

		if (data[0] instanceof String) {
			str = (String)data[0];
		} else if (data[0] instanceof Number) {
			str = ((Number)data[0]).toString();
		}else {
			sendMisMatchedTypeError(caster, runeIndex, 0);
			return new Object[] { null };
		}

		data = new Object[dataFormat.length];
		return new Object[]{
			str
		};
	}
}
