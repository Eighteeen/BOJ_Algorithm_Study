using System;

public class Test
{
	public static void Main()
	{
		int line = Read_ints(false)[0];
		int[] input = new int[2];
		int[,] bulk = new int[line, 2];
		int cnt;

		for (int i = 0; i < line; i++)
		{
			input = Read_ints(true);
			bulk[i, 0] = input[0];
			bulk[i, 1] = input[1];
		}

		for (int i = 0; i < line; i++)
		{
			cnt = 0;
			for (int j = 0; j < line; j++)
			{
				if (bulk[j, 0] > bulk[i, 0] && bulk[j, 1] > bulk[i, 1])
					cnt++;
			}
			Console.Write(cnt + 1 + " ");
		}
	}
	static int[] Read_ints(bool isArr)
	{
		string input = Console.ReadLine();
		if (isArr == false)
		{
			int[] intArr = { Convert.ToInt32(input) };
			return intArr;
		}
		else
		{
			string[] strArr = input.Split(' ');
			int[] intArr = { Convert.ToInt32(strArr[0]), Convert.ToInt32(strArr[1]) };
			return intArr;
		}

	}
}