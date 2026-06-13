package com.minerkid08.telemetryui

class ConsoleInput : InputManager
{
	private var up = false;
	private var down = false;
	private var left = false;
	private var right = false;

	fun update()
	{
		up = false;
		down = false;
		left = false;
		right = false;
		val str = readln();
		if (str.isEmpty())
			return;
		if (str[0] == 'w')
			up = true;
		if (str[0] == 's')
			down = true;
		if (str[0] == 'a')
			left = true;
		if (str[0] == 'd')
			right = true;
	}

	override fun getUp() = up;
	override fun getDown() = down;
	override fun getLeft() = left;
	override fun getRight() = right;
}

class ConsoleOutput : Output
{
	override fun addLine(line: String)
	{
		println(line);
	}

	override fun endFrame()
	{

	}
}

fun main()
{
	val input = ConsoleInput();
	val output = ConsoleOutput();
	val ui = Ui(input, output, 40);

	/*val a = IntPtr();
val b = IntPtr();
val c = IntPtr();
val d = FloatPtr();
val e = BoolPtr();
val f = IntPtr();

val items = ArrayList<String>();
items.add("thing 1");
items.add("thing 2");
items.add("thing 3");
items.add("thing 4");
items.add("thing 5");

while (true)
{
	input.update();
	if (ui.button("exit"))
		return;
	ui.dropdown("items", f, items);

	ui.intInput("a", a, 1);
	ui.intInput("a1", a, 1);
	ui.intInput("a2", a, 1);
	ui.intInput("a3", a, 1);
	ui.intInput("a4", a, 1);
	ui.intInput("a5", a, 1);
	ui.intInput("a6", a, 1);
	ui.intInput("a7", a, 1);
	ui.intInput("a8", a, 1);
	ui.intInput("a9", a, 1);
	ui.intInput("a10", a, 1);
	ui.intInput("a11", a, 1);
	ui.intInput("a12", a, 1);
	ui.seperator();
	if (ui.treeNode("something", false))
	{
		if (b.value > 2)
			ui.intInput("c", c, 1);
		ui.intInput("b", b, 1);
		ui.text("text");
		if (ui.treeNode("something else", false))
		{
			ui.floatInput("d", d, 0.5f);
			ui.sameLine();
			ui.checkbox("e", e);
			ui.seperator();
			ui.text("some text :)");
			ui.treePop();
		}
		ui.treePop();
	}
	ui.update();
}*/

	val step = IntPtr();
	val thing = IntPtr();

	while (true)
	{

		input.update();
		if (ui.button("exit"))
			return;

	ui.intInput("step", step, 1);
		ui.intInput("thing", thing, step.value);

		ui.update();
	}
}