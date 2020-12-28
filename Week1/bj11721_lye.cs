using System;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();

		for (int i = 0; i < input.Length; i++)
		{
			Console.Write(input[i].ToString());
			if (i % 10 == 9) { Console.Write("\n"); }

		}
	}
}