using System;

public class Test
{
	public static void Main()
	{
		string input;
		int total;
		int sum = 0;

		input = Console.ReadLine();
		total = Convert.ToInt32(input);

		for (int i = 0; i < 9; i++)
		{
			input = Console.ReadLine();
			sum += Convert.ToInt32(input);
		}

		Console.WriteLine(total - sum);
	}
}