using System;

//// 깔끔합니다!
public class Test
{
	public static void Main()
	{
		int line = Read_int();
		int sangduck = 100, changyoung = 100;
		int sangScore, changScore;

		for (int i = 0; i < line; i++)
		{
			int[] scores = Read_ints();
			changScore = scores[0];
			sangScore = scores[1];

			if (changScore != sangScore)
			{
				if (changScore > sangScore) sangduck -= changScore;
				else changyoung -= sangScore;
			}

		}
		Console.Write(changyoung + "\n" + sangduck);
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