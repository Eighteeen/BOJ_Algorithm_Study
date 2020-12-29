using System;

public class Test
{
	public static void Main()
	{
		string input;
		int num;
		while (true)
		{
			input = Console.ReadLine();
			num = input.Length;
			if (input == "END") break;
			for (int i = num; i > 0; i--)
			{
				Console.Write(input[i - 1].ToString());
			}
			Console.Write("\n");
		}
	}
}