package net.picklestring.flux_weavers.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.items.runes.RuneItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CastingContext {
	public boolean isStack = false;
	public boolean isCanceled = false;
	public Stack<Object> stack = new Stack<>();
	public int currentIndex = 0;
	public HashMap<String, Object> extraContext = new HashMap<>();

	public static void cast(DefaultedList<ItemStack> spell, PlayerEntity caster, CastingContext context) {
		FluxWeavers.LOGGER.info("Executing spell");
		while(context.currentIndex < spell.size()) {
			ItemStack stack = spell.get(context.currentIndex);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof RuneItem rune) {
					if (context.isStack) {
						FluxWeavers.LOGGER.info("Executing rune at: "+context.currentIndex+" type of "+rune.getClass().getSimpleName());
						Object[] stackObjects = rune.getValue(ListUtils.listToDefaultedList(spell, ItemStack.EMPTY), context.currentIndex, caster, new Vec3d(caster.getX(), caster.getY(), caster.getZ()), caster.getWorld(), context);
						if (stackObjects != null) {
							for (Object stackObject : stackObjects) {
								context.stack.push(stackObject);
							}
						}
						CastingContext.PrintStack(context);
						if (context.isCanceled) {
							return;
						}
					}
					rune.onCast(ListUtils.listToDefaultedList(spell, ItemStack.EMPTY), context.currentIndex, caster, new Vec3d(caster.getX(), caster.getY(), caster.getZ()), caster.getWorld(), context);

					if (rune.dataFormat != null) {
						rune.data = new Object[rune.dataFormat.length];
					}
					else {
						rune.data = new Object[0];
					}
				}
			}

			context.currentIndex += 1;
		}
	}

	public static void PrintStack(CastingContext context) {
		FluxWeavers.LOGGER.info("-----| Stack |-----");
		FluxWeavers.LOGGER.info("Stack size: "+context.stack.size());
		for (int i = 0; i < context.stack.size(); i++) {
			FluxWeavers.LOGGER.info(i+": "+context.stack.get(i).getClass().getTypeName()+" "+passStackValueToString(context.stack.get(i)));
		}
		FluxWeavers.LOGGER.info("-------------------");
	}

	private static String passStackValueToString(Object obj) {
		if (obj instanceof String str) {
			return str;
		} else if (obj instanceof Number num) {
			return num.doubleValue()+"";
		} else if (obj instanceof Vector3 vec3) {
			return "("+vec3.getX()+", "+vec3.getY()+", "+vec3.getZ()+")";
		} else if (obj instanceof Boolean bool) {
			return bool.toString();
		}
		return "";
	}
}
