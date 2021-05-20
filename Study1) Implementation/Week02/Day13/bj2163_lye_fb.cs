using System;

public class Test
{
	public static void Main()
	{
		int[] chocolate = Read_ints();
		int width = chocolate[0];
		int height = chocolate[1];

		Console.Write(width * height - 1);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
}