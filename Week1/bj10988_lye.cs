using System;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		int l = input.Length;
		int cnt = 0;

		for (int i = 0; i < l / 2; i++)
		{
			if (input[i].ToString() == input[l - i - 1].ToString())
			{
				cnt++;
			}
		}

		if (cnt == l / 2) { Console.WriteLine("1"); }
		else { Console.WriteLine("0"); }
	}
}