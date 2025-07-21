package com.minerkid08.telemetryui

class Button(id: String): UiElement(id)
{
	override fun render(target: Output, selected: Boolean)
	{
		addLine(target, selected, id);
	}

	override fun update(input: InputManager, selected: Boolean): Boolean
	{
		return selected && input.getRight();
	}
}

class IntInput(id: String, private val value: IntPtr, private val step: Int): UiElement(id)
{
	override fun render(target: Output, selected: Boolean)
	{
		addLine(target, selected, id + ": ${value.value}");
	}

	override fun update(input: InputManager, selected: Boolean): Boolean
	{
		if(!selected)
			return false;
		if(input.getRight())
		{
			value.value += step;
			return true;
		}
		if(input.getLeft())
		{
			value.value -= step;
			return true;
		}
		return false;
	}
}

class FloatInput(id: String, private val value: FloatPtr, private val step: Float): UiElement(id)
{
	override fun render(target: Output, selected: Boolean)
	{
		addLine(target, selected, id + ": ${value.value}");
	}

	override fun update(input: InputManager, selected: Boolean): Boolean
	{
		if(!selected)
			return false;
		if(input.getRight())
		{
			value.value += step;
			return true;
		}
		if(input.getLeft())
		{
			value.value -= step;
			return true;
		}
		return false;
	}
}

class Checkbox(id: String, private val value: BoolPtr): UiElement(id)
{
	override fun render(target: Output, selected: Boolean)
	{
		addLine(target, selected, id + ": " + if(value.value) "[V]" else "[X]")
	}

	override fun update(input: InputManager, selected: Boolean): Boolean
	{
		if(!selected)
			return false;
		if(input.getRight())
		{
			value.value = !value.value;
			return true;
		}
		return false;
	}
}

class Dropdown(
	id: String,
	parent: UiContainer,
	private val value: IntPtr,
	private val listItems: List<String>
):
	UiContainer(id, parent)
{
	init
	{
		for(item in listItems)
			items.add(Button(item));
	}

	override fun render(target: Output, selected: Boolean)
	{
		addLine(target, selected && selectedIndex == -1, "$id: ${listItems[value.value]}");
		if(opened)
		{
			target.addIndent();
			for((i, elem) in items.withIndex())
				elem.render(target, i == selectedIndex && selected);
			target.removeIndent();
		}
	}

	override fun update(input: InputManager, selected: Boolean): Boolean
	{
		if(selectedIndex == -1 && selected)
		{
			if(input.getRight())
			{
				selectedIndex = -1;
				opened = !opened;
			}
			return false;
		}
		for((i, item) in items.withIndex())
		{
			if(item.update(input, selectedIndex == i))
			{
				value.value = i;
				close();
				return true;
			}
		}

		return false;
	}

	override fun scrollUp(): Boolean
	{
		if(!opened) return false;
		if(selectedIndex == -1)
			return false;
		selectedIndex--;
		return true;
	}

	override fun scrollDown(): Boolean
	{
		if(!opened) return false;
		if(selectedIndex == items.size - 1)
			return false;
		selectedIndex++;
		return true;
	}
}