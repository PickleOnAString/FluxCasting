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

public class TargetTrackerRune extends RuneItem {
	public TargetTrackerRune(Settings settings) {
		super(
			settings,
			null,
			new Type[]{
				Vector3.class
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
		if (!context.extraContext.containsKey("Target")) return new Object[] {null};
		return new Object[] {
			context.extraContext.get("Target")
		};
	}
}
