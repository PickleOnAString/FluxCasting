package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;

public class KineticMomentumRune extends RuneItem{
	public KineticMomentumRune(Settings settings) {
		super(settings, new Type[][]{ new Type[]{LivingEntity.class}, new Type[]{Vector3.class} }, null, new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/kinetic_momentum_rune_overlay.png"));
		//data = DefaultedList.ofSize(60, null).stream().toList();
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, index, caster, pos, world);

		LivingEntity entity = getDataOrDefault(0, null, LivingEntity.class);
		Vector3 vec = getDataOrDefault(1, null, Vector3.class);

		if (entity == null) {
			caster.sendMessage(Text.literal("Rune at: index "+index+", LivingEntity at Index 0, is null").formatted(Formatting.RED), false);
			return;
		}
		if (vec == null) {
			caster.sendMessage(Text.literal("Rune at: index "+index+", Vector3 at Index 1 is null").formatted(Formatting.RED), false);
			return;
		}

		entity.addVelocity(vec.VectorToVec3d());
		entity.velocityModified = true;

		data = new Object[data.length];
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		return null;
	}
}
