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

public class NumericalImbuementRune extends RuneItem {
	public NumericalImbuementRune(Settings settings) {
		super(settings,
			null,
			new Type[]{
				Integer.class
			},
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/numerical_imbuement_rune_overlay.png")
		);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return;
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return new Object[]{
			Integer.parseInt(getStringPartOrDefault(1, "0", inventory.get(runeIndex)))
		};
	}
}
