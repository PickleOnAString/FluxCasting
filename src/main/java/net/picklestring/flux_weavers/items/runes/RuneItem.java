package net.picklestring.flux_weavers.items.runes;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.InternalizedFluxComponent;
import net.picklestring.flux_weavers.registries.ComponentRegistry;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.Vector3;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public abstract class RuneItem extends Item {
	public Object[] data;
	public Type[][] dataFormat;
	public Type[] outputType;
	public Identifier OVERLAY_TEXTURE;

	public RuneItem(Settings settings, Type[][] dataFormat, Type[] outputType, Identifier overlayTexture) {
		super(settings);
		this.dataFormat = dataFormat;
		this.outputType = outputType;
		this.OVERLAY_TEXTURE = overlayTexture;
		if (dataFormat != null) {
			data = new Object[dataFormat.length];
		}
		else {
			data = new Object[0];
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		if (outputType != null) {
			if (outputType.length != 0) {
				tooltip.add(Text.literal("Output:").formatted(Formatting.GRAY));
				for (int i = 0; i < outputType.length; i++) {
					tooltip.add(Text.literal("  > Index ").formatted(Formatting.GRAY).append(Text.literal(String.valueOf(i)).formatted(Formatting.GOLD)).append(Text.literal(": ").formatted(Formatting.GRAY)).append(Text.literal(simplifyTypeName(outputType[i].getTypeName())).formatted(Formatting.AQUA)));
				}
			}
		}
		if (dataFormat != null) {
			if (dataFormat.length != 0) {
				tooltip.add(Text.literal("Input:").formatted(Formatting.GRAY));
				for (int i = 0; i < dataFormat.length; i++) {
					tooltip.add(Text.literal("  > Index ").formatted(Formatting.GRAY).append(Text.literal(String.valueOf(i)).formatted(Formatting.GOLD)).append(Text.literal(": ").formatted(Formatting.GRAY)).append(getDataText(i)));
				}
			}
		}
	}

	public abstract void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context);
	public abstract Object[] getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world, CastingContext context);

	public void executeInserters(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world, CastingContext context) {
		for (int i = 0; i < 4; i++) {
			if (RunicConduitRune.getAdjacentIndexFromDirection(index, i) >= 0 && RunicConduitRune.getAdjacentIndexFromDirection(index, i) < inventory.size()) {
				ItemStack newItem = inventory.get(RunicConduitRune.getAdjacentIndexFromDirection(index, i));
				if (newItem.getItem() instanceof RunicConduitRune) {
					if (((RunicConduitRune) newItem.getItem()).insertDirection == RunicConduitRune.invertDirection(RunicConduitRune.intToDirection(i))) {
						((RunicConduitRune) newItem.getItem()).getValue(inventory, RunicConduitRune.getAdjacentIndexFromDirection(index, RunicConduitRune.intToDirection(i)), caster, pos, world, context);
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

	public void stringPartOrStackPop(CastingContext context, DefaultedList<ItemStack> inventory, int itemIndex, int dataIndex) {
		setDataToStringPartIfValid(dataIndex, inventory.get(itemIndex));
		if (data[dataIndex] == null) {
			data[dataIndex] = context.stack.pop();
		}
	}

	public boolean isDataNull(PlayerEntity caster, DefaultedList<ItemStack> inventory, int itemIndex, int dataIndex) {
		if (data[dataIndex] == null) {
			data[dataIndex] = getStringPart(1, inventory.get(itemIndex));
			if (data[dataIndex] == null) {
				if (caster != null) caster.sendMessage(Text.translatable("rune.flux_weavers.error.missing_data", itemIndex, 0), false);
				return true;
			}
		}
		return false;
	}

	public void sendMisMatchedTypeError(PlayerEntity caster, int itemIndex, int dataIndex) {
		if (caster != null) caster.sendMessage(Text.translatable("rune.flux_weavers.error.wrong_data_type", itemIndex, dataIndex)
			.append(Text.literal(simplifyTypeName(data[dataIndex].getClass().getTypeName())).formatted(Formatting.AQUA))
			.append(Text.literal(" != "))
			.append(getDataText(dataIndex)), false);
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

	public String getStringPart(int index, ItemStack itemStack) {
		String name = itemStack.getName().getString();
		String[] strings = name.split(" ?: ?");
		if (index >= strings.length) return null;
		if (Objects.equals(strings[index], "null")) return null;
		return strings[index];
	}

	public void setDataToStringPartIfValid(int dataIndex, ItemStack itemStack) {
		String name = itemStack.getName().getString();
		String[] strings = name.split(" ?: ?");
		if (dataIndex+1 >= strings.length) return;
		if (Objects.equals(strings[dataIndex+1], "null")) {
			data[dataIndex] = null;
			return;
		}
		data[dataIndex] = strings[dataIndex+1];
	}

	public Vector3 loadVec3FromData(int dataIndex, int runeIndex, PlayerEntity caster) {
		if (data[dataIndex] instanceof String) {
			return stringToVec((String)data[dataIndex], caster, runeIndex, dataIndex);
		} else if (data[dataIndex] instanceof Vector3) {
			return (Vector3)data[dataIndex];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, dataIndex);
			return null;
		}
	}

	public Boolean loadBooleanFromData(int dataIndex, int runeIndex, PlayerEntity caster) {
		if (data[dataIndex] instanceof String) {
			return stringToBoolean((String)data[dataIndex], caster, runeIndex, dataIndex);
		} else if (data[dataIndex] instanceof Boolean) {
			return (Boolean) data[dataIndex];
		}else {
			sendMisMatchedTypeError(caster, runeIndex, dataIndex);
			return null;
		}
	}

	public Number loadNumberFromData(int dataIndex, int runeIndex, PlayerEntity caster) {
		FluxWeavers.LOGGER.info("Loading Number at index: "+runeIndex);
		if (data[dataIndex] instanceof String str) {
			FluxWeavers.LOGGER.info("Parsing String to Number at index: "+runeIndex);
			return Double.parseDouble(str);
		} else if (data[dataIndex] instanceof Number num) {
			return num;
		}else {
			sendMisMatchedTypeError(caster, runeIndex, dataIndex);
			return null;
		}
	}

	public Integer loadIntegerFromData(int dataIndex, int runeIndex, PlayerEntity caster) {
		if (data[dataIndex] instanceof String str) {
			return Integer.parseInt(str);
		}else if (data[dataIndex] instanceof Integer integer) {
			return integer;
		}else if(data[dataIndex] instanceof Number num) {
			return num.intValue();
		}else {
			sendMisMatchedTypeError(caster, runeIndex, dataIndex);
			return null;
		}
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

	public void consumeFlux(int amount, PlayerEntity caster) {
		InternalizedFluxComponent component = ComponentRegistry.INTERNALIZED_FLUX.get(caster);
		component.setValue(component.getValue()-amount);
	}

	public boolean canCastFluxCost(int amount, PlayerEntity caster) {
		InternalizedFluxComponent component = ComponentRegistry.INTERNALIZED_FLUX.get(caster);
		return component.getValue() >= amount;
	}

	public Vector3 stringToVec(String str, PlayerEntity caster, int runeIndex, int dataIndex) {
		String[] strs = str.split(" ?,?");
		if (strs.length != 3) {
			sendMisMatchedTypeError(caster, runeIndex, dataIndex);
			return null;
		};
		return new Vector3(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
	}

	public Boolean stringToBoolean(String str, PlayerEntity caster, int runeIndex, int dataIndex) {
		if (str.equalsIgnoreCase("true")) {
			return Boolean.TRUE;
		}
		else if (str.equalsIgnoreCase("false")) {
			return Boolean.FALSE;
		}
		sendMisMatchedTypeError(caster, runeIndex, dataIndex);
		return null;
	}
}
