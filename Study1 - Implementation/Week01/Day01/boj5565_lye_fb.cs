using System;

public class Test
{
	public static void Main()
	{
		int total;
		int sum = 0;

		total = Read_int();

		for (int i = 0; i < 9; i++)
		{
			sum += Read_int();
		}

		Console.WriteLine(total - sum);
	}

	public static int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}