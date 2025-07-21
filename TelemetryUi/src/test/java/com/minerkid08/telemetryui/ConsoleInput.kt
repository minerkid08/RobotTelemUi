package com.minerkid08.telemetryui

class ConsoleInput: InputManager
{
	private var up = false;
	private var down = false;
	private var left = false;
	private var right = false;

	fun update()
	{
		val str = readln();
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
	val ui = UI(input, output);

	val a = FloatPtr(0.0f);
	val b = IntPtr(0);
	val c = BoolPtr(false);

	while(true)
	{
		ui.update();
		if(ui.button("exit"))
			return;
		ui.floatInput("a", a, 1.0f);
		ui.intInput("b", b, 1);
		ui.checkbox("c", c);
	}
}