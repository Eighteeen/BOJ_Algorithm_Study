using System;

public class Test
{
	public static void Main()
	{
		string input;
		string[] arr = new string[4];
		int max = 0;
		int max_n = 0;

		for (int i = 0; i < 5; i++)
		{
			int sum = 0;
			input = Console.ReadLine();
			arr = input.Split(' ');
			for (int j = 0; j < 4; j++)
			{
				sum += Convert.ToInt32(arr[j]);
			}
			if (sum > max)
			{
				max = sum;
				max_n = i + 1;
			}

		}
		Console.WriteLine("{0} {1}", max_n, max);
	}
}