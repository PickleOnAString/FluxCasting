package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.utils.CastingContext;

import java.lang.reflect.Type;

public class GlyphicInscriptionRune extends RuneItem {

	public GlyphicInscriptionRune(Settings settings) {
		super(settings,
			null,
			new Type[] {
				String.class
			},
			Identifier.of(FluxCasting.ModID, "textures/gui/rune_overlay/text_input_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return;
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return new Object[]{
			getStringPart(1, inventory.get(runeIndex))
		};
	}
}
