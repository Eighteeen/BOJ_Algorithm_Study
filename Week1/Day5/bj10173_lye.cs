using System;

public class Test
{
	public static void Main()
	{
		string input;
		int index;

		while (true)
		{
			index = -1;
			input = Console.ReadLine();
			if (input == "EOI") return;

			index = input.IndexOf("nemo", StringComparison.OrdinalIgnoreCase);
			if (index == -1) Console.WriteLine("Missing");
			else Console.WriteLine("Found");
		}
	}
}