using System;

public class Test
{
	public static void Main()
	{
		string answer = "1 2 3 4 5 1 2 3 4 5";
		int studentsCnt = Convert.ToInt32(Console.ReadLine());

		for (int i = 0; i < studentsCnt; i++)
		{
			string input = Console.ReadLine();
			if(input == answer) Console.WriteLine(i + 1);
		}
	}
}