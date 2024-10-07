package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.utils.Vector3;

import java.lang.reflect.Type;

public class RendStoneRune extends RuneItem {
	public RendStoneRune(Settings settings) {
		super(settings,
			new Type[][]{ new Type[]{Vector3.class} },
			null,
			new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/debug_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		executeInserters(inventory, index, caster, pos, world);
		Vector3 blockPos = getDataOrDefault(0, Vector3.Vec3dToVector3(new Vec3d(0d, 0d, 0d)), Vector3.class);
		if (Math.abs(Vector3.Vec3dToVector3(pos).getMagnitude()-blockPos.getMagnitude()) > 15) {
			caster.sendMessage(Text.of("Rend Stone out of range of 15"), false);
			return;
		}
		if (!canCastFluxCost(10, caster)) {
			caster.sendMessage(Text.translatable("casting.flux_casting.out_of_flux"), false);
			return;
		}
		world.breakBlock(new BlockPos(blockPos.VectorToVec3i()), true);
		consumeFlux(10, caster);
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		return null;
	}
}
