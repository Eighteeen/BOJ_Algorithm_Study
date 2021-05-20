using System;

public class Test
{
	public static void Main()
	{
		int num;
		int jul;
		num = Read_int();
		jul = 2 * num - 1;

		for (int i = 0; i < jul; i++)
		{

			if (i < num)
				Stamp_star(0, i + 1);

			else
				Stamp_star(i, jul);

			if (i != jul - 1)
				Console.WriteLine();
		}
	}
	public static int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	public static void Stamp_star(int start, int end)
	{
		for (int i = start; i < end; i++)
			Console.Write("*");

	}
}