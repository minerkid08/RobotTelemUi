package com.minerkid08.telemetryui

fun main()
{
	val s = "NNNNWWWWNNNN_NNNNWWWWNNNN";
	setAnimations(s);
}

fun setAnimations(str: String)
{
	var animId = 0;

	var prevColor = 'N';
	var skipCount = 0;
	var startInd = 0;
	var color = 'N';
	for ((i, k) in str.withIndex())
	{
		if (k == '_')
		{
			skipCount++;
			continue;
		}
		val ledIndex = i - skipCount;
		if (prevColor != k)
		{
			if (prevColor != 'N')
			{
				println("color" + color);
				println("start ind" + startInd);
				println("end ind " + (ledIndex - 1));
				println("anim id " + animId);
				animId++;
			}
			startInd = ledIndex;
			if(k != 'N')
				color = k;
			prevColor = k;
		}
	}
	if (prevColor != 'N')
	{
		println("color" + color);
		println("start ind" + startInd);
		println("end ind " + 23);
		println("anim id " + animId);
	}
}