package com.minerkid08.telemetryui

class ConsoleInput: InputManager
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
		if(str.isEmpty())
			return;
		if(str[0] == 'w')
			up = true;
		if(str[0] == 's')
			down = true;
		if(str[0] == 'a')
			left = true;
		if(str[0] == 'd')
			right = true;
	}

	override fun getUp() = up;
	override fun getDown() = down;
	override fun getLeft() = left;
	override fun getRight() = right;
}

class ConsoleOutput: Output
{
	private var indentCount = -1;
	private var sameLine = false;
	private var line = "";
	override fun addLine(line: String, indent: String)
	{
		if(indentCount == -1)
			return;
		if(sameLine)
			this.line += "  $line";
		else
		{
			if(this.line.isNotEmpty())
				println(this.line);
			var str = "";
			for(i in 0 until indentCount)
				str += indent;
			this.line = str + line;
		}
		sameLine = false;
	}

	override fun endFrame()
	{
		indentCount = -1;
		sameLine = false;
		if(line.isNotEmpty())
			println(line);
		line = "";
	}

	override fun addIndent()
	{
		indentCount++;
	}

	override fun removeIndent()
	{
		indentCount--;
	}

	override fun sameLine()
	{
		sameLine = true;
	}
}

fun main()
{
	val input = ConsoleInput();
	val output = ConsoleOutput();
	val ui = Ui(input, output);

	val a = IntPtr();
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

	while(true)
	{
		input.update();
		if(ui.button("exit"))
			return;
		ui.dropdown("items", f, items);

		ui.intInput("a", a, 1);
		ui.seperator();
		if(ui.treeNode("something", false))
		{
			if(b.value > 2)
				ui.intInput("c", c, 1);
			ui.intInput("b", b, 1);
			ui.text("text");
			if(ui.treeNode("something else", false))
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
	}
}