using System;

public class Test
{
	public static void Main()
	{
		bool[] isBall = { true, false, false };
		int line = Read_int();
		int X, Y;
		bool tmp;
		for (int i = 0; i < line; i++)
		{
			//// Changes, X, Y.. 변수명이 좀 더 직관적이였으면 좋았을 것 같아요!
			int[] Changes = Read_numArr();
			X = Changes[0] - 1;
			Y = Changes[1] - 1;
			tmp = isBall[Y];
			isBall[Y] = isBall[X];
			isBall[X] = tmp;
		}
		//// IndexOf 활용 좋네용
		Console.Write(Array.IndexOf(isBall, true) + 1);
	}
	static int[] Read_numArr()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}