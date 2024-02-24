package net.picklestring.flux_casting.items.runes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.picklestring.flux_casting.FluxCasting;

public class InserterRune extends RuneItem {

	public Direction insertDirection;
	public Direction pullDirection;

	public InserterRune(Settings settings, Direction insertDirection, Direction pullDirection) {
        super(settings);
        this.insertDirection = insertDirection;
        this.pullDirection = pullDirection;
    }

	@Override
	public void onCast(DefaultedList<ItemStack> inventory, int index) {
		return;
	}

	@Override
	public Object getValue(DefaultedList<ItemStack> inventory, int index) {
		FluxCasting.LOGGER.info("Inserter: getValue");
		ItemStack itemStack = inventory.get(getAdjacentIndexFromDirection(index, pullDirection));
		if (!itemStack.isEmpty() && itemStack.getItem() instanceof RuneItem)
		{
			FluxCasting.LOGGER.info("Inserter: getValue: check valid rune");
			Object object = ((RuneItem)itemStack.getItem()).getValue(inventory, getAdjacentIndexFromDirection(index, pullDirection));
			((RuneItem)inventory.get(getAdjacentIndexFromDirection(index, insertDirection)).getItem()).data.add(0, object);
			return object;
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
