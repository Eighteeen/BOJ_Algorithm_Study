using System;

public class Test
{
	public static void Main()
	{
		bool[] arr = new bool[31];
		int num;

		for (int i = 0; i < 28; i++)
		{
			num = Read_int();
			arr[num] = true;
		}

		for (int i = 1; i < 31; i++)
		{
			if (arr[i] == false)
				Console.WriteLine(i);
		}
	}

	public static int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}