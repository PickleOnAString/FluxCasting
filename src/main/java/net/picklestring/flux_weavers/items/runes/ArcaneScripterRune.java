package net.picklestring.flux_weavers.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;

import java.lang.reflect.Type;

public class ArcaneScripterRune extends RuneItem {
	public ArcaneScripterRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{
					String.class,
					Number.class}
			},
			null,
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/debug_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		String debugString = "";

		if (!context.isStack) {
			executeInserters(inventory, index, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, index, 0);
		}

		if (isDataNull(caster, inventory, index, 0)) return;

		if (data[0] instanceof Number) {
			debugString = ((Number)data[0]).toString();
		} else if (data[0] instanceof String) {
			debugString = (String)data[0];
		}
		else {
			sendMisMatchedTypeError(caster, index, 0);
			return;
		}

		caster.sendMessage(Text.literal(debugString), false);

		data = new Object[dataFormat.length];
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
