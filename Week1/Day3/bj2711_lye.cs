using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		int jul, loc;
		jul = ts.Read_int();

		for (int i = 0; i < jul; i++)
		{
			string words = Console.ReadLine();
			string[] arr = words.Split(' ');
			loc = Convert.ToInt32(arr[0]);
			words = arr[1];

			for (int j = 0; j < words.Length; j++)
			{
				if (j != loc - 1)
				{
					Console.Write(words[j].ToString());
				}
			}

			Console.WriteLine();
		}

	}

	public int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}