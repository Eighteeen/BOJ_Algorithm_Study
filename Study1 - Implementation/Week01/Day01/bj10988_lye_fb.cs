using System;

public class Test
{
	public static int Main()
	{
		string input;
		input = Console.ReadLine();

		int len = input.Length;

		for (int i = 0; i < len / 2; i++)
		{
			if (input[i].ToString() != input[len - i - 1].ToString())
			{
				Console.WriteLine("0");
				return 0;
			}
		}

		Console.WriteLine("1");
		return 0;
	}
}