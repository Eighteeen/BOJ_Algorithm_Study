using System;

public class Test
{
	public static void Main()
	{
		int dontDrive = Read_int();
		int[] allCars = Read_ints();
		int cnt = 0;

		for (int i = 0; i < allCars.Length; i++)
		{
			if (dontDrive == allCars[i])
				cnt++;
		}
		Console.Write(cnt);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split();
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
}