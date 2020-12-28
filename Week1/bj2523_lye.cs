using System;

public class Test
{
	public static void Main()
	{
		string input;
		int num;
		int jul;
		input = Console.ReadLine();
		num = Convert.ToInt32(input);
		jul = 2 * num - 1;

		for (int i = 0; i < jul; i++)
		{

			if (i < num)
			{
				for (int j = 0; j < i + 1; j++)
				{
					Console.Write("*");
				}
			}
			else
			{
				for (int j = i; j < jul; j++)
				{
					Console.Write("*");
				}
			}
			if (i != jul - 1) Console.Write("\n");
		}
	}
}