using System;

public class Test
{
	public static void Main()
	{
		int min = 99, sum = 0;
		int now;

		for (int i = 0; i < 7; i++)
		{
			now = Read_int();

			if (now % 2 != 0)
			{
				sum += now;
				if (now < min)
					min = now;
			}

		}
		Console.Write(sum == 0 ? "-1" : sum + "\n" + min);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}