using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int line = Read_int();
		int cnt = 1;
		Stack stick = new Stack();
		for (int i = 0; i < line; i++)
		{
			stick.Push(Read_int());
		}
		int max = Convert.ToInt32(stick.Pop());
		for (int i = 1; i < line; i++)
		{
			int nowStick = Convert.ToInt32(stick.Pop());
			if (nowStick > max)
			{
				cnt++;
				max = nowStick;
			}
		}
		Console.Write(cnt);
	}
	static int Read_int()
	{
		int input = Convert.ToInt32(Console.ReadLine());
		return input;
	}
}