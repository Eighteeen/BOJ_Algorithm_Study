using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		int line = ts.Read_int();

		for (int i = 0; i < line; i++)
		{
			ts.Print_star(line + 1);
			Console.Write("\n ");
			ts.Print_star(line);
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
