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

public class SightlineSeekerRune extends RuneItem {
	public SightlineSeekerRune(Settings settings) {
		super(settings, new Type[][]{ new Type[]{LivingEntity.class} }, Vector3.class, new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/sightline_seeker_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, runeIndex, caster, pos, world);

		LivingEntity entity = getDataOrDefault(0, null, LivingEntity.class);

		if (entity == null) {
			caster.sendMessage(Text.literal("Rune at: index "+runeIndex+", LivingEntity at Index 0 is null").formatted(Formatting.RED), false);
			return null;
		}

		data = new Object[data.length];
		return Vector3.Vec3dToVector3(Vec3d.fromPolar(entity.getPitch(), entity.headYaw));
	}
}
