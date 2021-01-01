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

		string[] strNumArr = new string[num];

		input = Console.ReadLine();
		strNumArr = input.Split(' ');

		for (int i = 0; i < num; i++)
		{
			if (Convert.ToInt32(strNumArr[i]) < min)
			{
				Console.Write(Convert.ToInt32(strNumArr[i]));
				Console.Write(" ");
			}
		}

	}
}