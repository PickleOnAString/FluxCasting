package net.picklestring.flux_casting.items.runes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_casting.FluxCasting;
import net.picklestring.flux_casting.items.FluxWand;
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public class RunicConduitRune extends RuneItem {

	public Direction insertDirection;
	public Direction pullDirection;

	public RunicConduitRune(Settings settings, Direction insertDirection, Direction pullDirection) {
        super(settings,
			new Type[][]{ new Type[]{Integer.class}, new Type[]{Integer.class} },
			null,
			new Identifier(FluxCasting.ModID, "textures/gui/rune_overlay/left_to_right_inserter_rune_overlay.png"));

        this.insertDirection = insertDirection;
        this.pullDirection = pullDirection;
    }

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index, PlayerEntity caster, Vec3d pos, World world) {
		return;
	}

	/*@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		Integer insertIndex = geIntFromName(2, inventory.get(runeIndex), 0);

		ItemStack itemStack = inventory.get(getAdjacentIndexFromDirection(runeIndex, pullDirection));
		if (itemStack.isEmpty() || !(itemStack.getItem() instanceof RuneItem pullRune)) return null;
		;
        Object object = pullRune.getValue(inventory, getAdjacentIndexFromDirection(runeIndex, pullDirection), caster, pos, world);

		if (object == null) caster.sendMessage(Text.literal("TestTest"), false);

		if (!((RuneItem)inventory.get(getAdjacentIndexFromDirection(runeIndex, insertDirection)).getItem()).checkIsDataTypeCorrect(insertIndex, object)) {
			data = new Object[data.length];
			return throwFailedInsertError(runeIndex, object.getClass(), ((RuneItem)inventory.get(getAdjacentIndexFromDirection(runeIndex, insertDirection)).getItem()).getDataTextString(insertIndex)+"huh?", caster, world, pos);
		}

		((RuneItem)inventory.get(getAdjacentIndexFromDirection(runeIndex, insertDirection)).getItem()).data[insertIndex] = object;

		data = new Object[data.length];
		return object;
	}*/

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int runeIndex, PlayerEntity caster, Vec3d pos, World world) {
		Integer insertIndex = geIntFromName(2, inventory.get(runeIndex), 0);
		ItemStack pullItem = inventory.get(getAdjacentIndexFromDirection(runeIndex, pullDirection));
		if (pullItem.isEmpty() || !(pullItem.getItem() instanceof RuneItem pullRune)) return null;

		Object object = pullRune.getValue(inventory, getAdjacentIndexFromDirection(runeIndex, pullDirection), caster, pos, world);

		ItemStack insertItem = inventory.get(getAdjacentIndexFromDirection(runeIndex, insertDirection));
		if(!isInsertDataTypeCorrect(insertIndex, object, insertItem)) {
			data = new Object[data.length];
			return throwFailedInsertError(runeIndex, object.getClass(), ((RuneItem) insertItem.getItem()).getDataTextString(insertIndex), caster, world, pos);
		}

		((RuneItem) insertItem.getItem()).data[insertIndex] = object;

		data = new Object[data.length];
		return object;
	}

	private boolean isInsertDataTypeCorrect(Integer insertIndex, Object object, ItemStack insertItem){
		return ((RuneItem) insertItem.getItem()).checkIsDataTypeCorrect(insertIndex, object);
	}

	public Object throwFailedInsertError(int index, Type insertType, String dataFormatTypeString, PlayerEntity caster, World world, Vec3d pos)
	{
		String error = "Rune at: index "+index+", Failed to insert because DataFormat type: "+dataFormatTypeString+" and Inserted type: "+simplifyTypeName(insertType.getTypeName())+" do not match!";
		if (caster == null)
		{
			Collection<ServerPlayerEntity> players = PlayerLookup.around((ServerWorld) world, pos, 10d);
			players.forEach(player -> player.sendMessage(Text.literal(error).formatted(Formatting.RED), false));
		}
		else
		{
			caster.sendMessage(Text.literal(error).formatted(Formatting.RED), false);
		}
		return null;
	}

	public static int getAdjacentIndexFromDirection(int index, Direction dir)
	{
		int newIndex = index;
		switch (dir)
		{
			case Left -> newIndex -=1;
			case Right -> newIndex += 1;
            case Up -> newIndex -= 9;
            case Down -> newIndex += 9;

		}
		return newIndex;
	}

	public static int getAdjacentIndexFromDirection(int index, int dir)
	{
		int newIndex = index;
		switch (dir)
		{
			case 0 -> newIndex -=1;
			case 1 -> newIndex += 1;
			case 2 -> newIndex -= 9;
			case 3 -> newIndex += 9;

		}
		return newIndex;
	}

	public static Direction intToDirection(int dir)
	{
		Direction direction = null;
		switch (dir)
		{
			case 0 -> direction = Direction.Left;
			case 1 -> direction = Direction.Right;
			case 2 -> direction = Direction.Up;
			case 3 -> direction = Direction.Down;
		}
		return direction;
	}

	public static Direction invertDirection(Direction dir)
	{
		Direction invertedDir = null;
		switch (dir)
		{
			case Left -> invertedDir = Direction.Right;
			case Right -> invertedDir = Direction.Left;
			case Up -> invertedDir = Direction.Down;
            case Down -> invertedDir = Direction.Up;
		}
		return invertedDir;
	}

	public enum Direction
	{
		Left,
		Right,
		Up,
		Down
	}
}
