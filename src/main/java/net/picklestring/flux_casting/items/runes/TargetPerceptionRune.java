package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;

public class TargetPerceptionRune extends RuneItem {
	public TargetPerceptionRune(Settings settings, Identifier overlayTexture) {
		super(settings, new Type[][]{ new Type[]{Vector3.class, Vector3.class} }, LivingEntity.class, overlayTexture);
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		Vector3 lookVec = getDataOrDefault(0, null, Vector3.class);
		Vector3 positionVec = getDataOrDefault(1, null, Vector3.class);

		if (lookVec == null) {
			caster.sendMessage(Text.literal("Rune at: index "+runeIndex+", Vector3 at Index 0, is null").formatted(Formatting.RED), false);
		}
		if (positionVec == null) {
			caster.sendMessage(Text.literal("Rune at: index "+runeIndex+", Vector3 at Index 1, is null").formatted(Formatting.RED), false);
		}

		//ProjectileUtil.raycast()

		return null;
	}
}
