using System;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		int len = input.Length;
		int sum1 = 0, sum2 = 0;

		for (int i = 0; i < len / 2; i++)
		{
			sum1 += Convert.ToInt32(input[i]);
			sum2 += Convert.ToInt32(input[len / 2 + i]);
		}

		if (sum1 == sum2) Console.WriteLine("LUCKY");
		else Console.WriteLine("READY");
	}
}