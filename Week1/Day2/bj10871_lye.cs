using System;

public class Test
{
	public static void Main()
	{
		string input;
		string[] arr = new string[2];
		int num, min;

		input = Console.ReadLine();
		arr = input.Split(' ');
		num = Convert.ToInt32(arr[0]);
		min = Convert.ToInt32(arr[1]);

		string[] soo = new string[num];

		input = Console.ReadLine();
		soo = input.Split(' ');

		for (int i = 0; i < num; i++)
		{
			if (Convert.ToInt32(soo[i]) < min)
			{
				Console.Write(Convert.ToInt32(soo[i]));
				Console.Write(" ");
			}
		}

	}
}