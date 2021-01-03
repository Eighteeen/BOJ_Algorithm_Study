using System;

public class Test
{
	public static void Main()
	{
		int[] numArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		int[] section = new int[2];
		int len;

		for (int i = 0; i < 10; i++)
		{
			section = Read_ints();
			len= = section[1] - section[0] + 1;
			Array.Reverse(numArr, section[0] - 1, len);
		}

		for (int i = 0; i < 20; i++)
		{
			Console.Write(numArr[i] + " ");
		}
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int[] result = { Convert.ToInt32(strArr[0]), Convert.ToInt32(strArr[1]) };
		return result;
	}
}