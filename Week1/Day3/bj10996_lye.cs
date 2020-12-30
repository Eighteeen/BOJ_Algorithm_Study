using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		int jul;
		jul = ts.Read_int();

		for (int i = 0; i < jul; i++)
		{
			ts.Print_star(jul + 1);
			Console.Write("\n ");
			ts.Print_star(jul);
			Console.WriteLine();
		}

	}

	public void Print_star(int n)
	{
		for (int i = 0; i < n / 2; i++)
		{
			Console.Write("* ");
		}
	}
	public int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}