using System;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		int len = input.Length;
		int half_len = len / 2;
		int left = 0, right = 0;

		for (int i = 0; i < half_len; i++)
		{
			left += Convert.ToInt32(input[i]);
			right += Convert.ToInt32(input[half_len + i]);
		}

		if (left == right) Console.WriteLine("LUCKY");
		else Console.WriteLine("READY");
	}
}