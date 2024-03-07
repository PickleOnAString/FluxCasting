package net.picklestring.flux_casting.items.runes;

import com.sun.jna.platform.win32.WinUser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.InternalizedFluxComponent;
import net.picklestring.flux_casting.items.FluxWand;
import net.picklestring.flux_casting.registries.ComponentRegistry;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RuneItem extends Item {
	public Object[] data;
	public Type[][] dataFormat;
	public Type outputType;
	public Identifier OVERLAY_TEXTURE;

	public RuneItem(Settings settings, Type[][] dataFormat, Type outputType, Identifier overlayTexture) {
		super(settings);
		this.dataFormat = dataFormat;
		this.outputType = outputType;
		this.OVERLAY_TEXTURE = overlayTexture;
		data = new Object[dataFormat.length];
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		if (outputType != null) {
			tooltip.add(Text.literal("Output: ").formatted(Formatting.GRAY).append(Text.literal(simplifyTypeName(outputType.getTypeName())).formatted(Formatting.AQUA)));
		}
		if (dataFormat.length != 0) {
			tooltip.add(Text.literal("Input:").formatted(Formatting.GRAY));
			for (int i = 0; i < dataFormat.length; i++) {
				tooltip.add(Text.literal("  > Index ").formatted(Formatting.GRAY).append(Text.literal(String.valueOf(i)).formatted(Formatting.GOLD)).append(Text.literal(": ").formatted(Formatting.GRAY)).append(getDataText(i)));
			}
		}
	}

	public abstract void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world);
	public abstract Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world);

	public void executeInserters(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		for (int i = 0; i < 4; i++) {
			if (RunicConduitRune.getAdjacentIndexFromDirection(index, i) >= 0 && RunicConduitRune.getAdjacentIndexFromDirection(index, i) < inventory.size()) {
				ItemStack newItem = inventory.get(RunicConduitRune.getAdjacentIndexFromDirection(index, i));
				if (newItem.getItem() instanceof RunicConduitRune) {
					if (((RunicConduitRune) newItem.getItem()).insertDirection == RunicConduitRune.invertDirection(RunicConduitRune.intToDirection(i))) {
						((RunicConduitRune) newItem.getItem()).getValue(inventory, RunicConduitRune.getAdjacentIndexFromDirection(index, RunicConduitRune.intToDirection(i)), caster, pos, world);
					}
				}
			}
		}
	}

	public String getDataTextString(int index) {
		if (dataFormat.length <= index) return "";
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < dataFormat[index].length; i++) {
            text.append(simplifyTypeName(dataFormat[index][i].getTypeName()));
			if (i+1 < dataFormat[index].length) {
				text.append(" | ");
			}
		}
		return text.toString();
	}

	public Text getDataText(int index) {
		MutableText text = Text.empty();
		for (int i = 0; i < dataFormat[index].length; i++)
		{
			text.append(simplifyTypeName(dataFormat[index][i].getTypeName())).formatted(Formatting.AQUA);
			if (i+1 < dataFormat[index].length)
			{
				text.append(Text.literal(" | ").formatted(Formatting.WHITE));
			}
		}
		return text;
	}

	public String simplifyTypeName(String name) {
		String[] nameParts = name.split("\\.");
		return nameParts[nameParts.length-1];
	}

	public boolean checkIsDataTypeCorrect(int index, Object type) {
		if (index < 0 || index >= dataFormat.length) {
			return false;
		}
		for (int i = 0; i < dataFormat[index].length; i++) {
			if (type == null) {
				return true;
			}
			if (dataFormat[index][i].getClass().isInstance(type.getClass())) {
                return true;
            }
		}
		return false;
		/* Get the expected class from dataFormat
		Class<?> expectedClass = dataFormat[index].getClass();

		// Check if the type object is an instance of the expected class
		return expectedClass.isInstance(type.getClass());*/
	}

	public String getStringPartOrDefault(int index, String defaultString, ItemStack itemStack) {
		String name = itemStack.getName().getString();
		String[] strings = name.split(" ?: ?");
		if (index >= strings.length) return defaultString;
		return strings[index];
	}

	public <T> T getDataOrDefault(int index, T defaultData, Class<T> clazz) {
		if (data[index] == null) {
			data[index] = defaultData;
		} else if (!clazz.isInstance(data[index])) {
			data[index] = defaultData;
		}
		return clazz.cast(data[index]);
	}

	public Double getDoubleFromName(int index, ItemStack stack, Double defaultData) {
		try {
			return Double.parseDouble(getStringPartOrDefault(index, defaultData.toString(), stack));
		}
		catch (NumberFormatException err)
		{
			return defaultData;
		}
	}

	public Float geFloatFromName(int index, ItemStack stack, Float defaultData) {
		try {
			return Float.parseFloat(getStringPartOrDefault(index, defaultData.toString(), stack));
		}
		catch (NumberFormatException err)
		{
			return defaultData;
		}
	}

	public Integer geIntFromName(int index, ItemStack stack, Integer defaultData) {
		try {
			return Integer.parseInt(getStringPartOrDefault(index, defaultData.toString(), stack));
		}
		catch (NumberFormatException err)
		{
			return defaultData;
		}
	}

	public Number geNumberFromName(int index, ItemStack stack, Number defaultData) {
		try {
			return Float.parseFloat(getStringPartOrDefault(index, defaultData.toString(), stack));
		}
		catch (NumberFormatException err)
		{
			return defaultData;
		}
	}

	public void consumeFlux(int amount, PlayerEntity caster)
	{
		InternalizedFluxComponent component = ComponentRegistry.INTERNALIZED_FLUX.get(caster);
		component.setValue(component.getValue()-amount);
	}
}
