package net.picklestring.flux_weavers.items.runes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;

public class RendStoneRune extends RuneItem {
	public RendStoneRune(Settings settings) {
		super(settings,
			new Type[][]{
				new Type[]{Vector3.class}
			},
			null,
			Identifier.of(FluxWeavers.ModID, "textures/gui/rune_overlay/rend_stone_rune_overlay.png"));
	}

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		if (!context.isStack) {
			executeInserters(inventory, index, caster, pos, world, context);
		}
		else {
			stringPartOrStackPop(context, inventory, index, 0);
		}
		Vector3 blockPos = null;

		if (isDataNull(caster, inventory, index, 0)) return;

		if (data[0] instanceof String) {
			String[] strs = ((String) data[0]).split(" ?,?");
			if (strs.length != 3) {
				sendMisMatchedTypeError(caster, index, 0);
				return;
			};
			blockPos = new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		} else if (data[0] instanceof Vector3) {
			blockPos = (Vector3)data[0];
		}else {
			sendMisMatchedTypeError(caster, index, 0);
			return;
		}

		if (Math.abs(Vector3.Vec3dToVector3(pos).getMagnitude()-blockPos.getMagnitude()) > 15) {
			caster.sendMessage(Text.of("Rend Stone out of range of 15"), false);
			return;
		}

		if (world.getBlockState(new BlockPos(blockPos.VectorToVec3i())) == Blocks.BEDROCK.getDefaultState()) return;

		if (!canCastFluxCost(10, caster)) {
			caster.sendMessage(Text.translatable("casting.flux_weavers.out_of_flux"), false);
			return;
		}
		world.breakBlock(new BlockPos(blockPos.VectorToVec3i()), true);
		consumeFlux(10, caster);
	}

	@Override
	public Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		return null;
	}
}
