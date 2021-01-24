using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int minutes = Convert.ToInt32(Console.ReadLine());
		int totalScore = 0;
		Stack scores = new Stack();
		Stack workMinutes = new Stack();

		for (int i = 0; i < minutes; i++)
		{
			int[] workInfo = ReadInt();
			int isWork = workInfo[0];
			int nowScore, nowMinute;

			if (isWork == 1)
			{
				nowScore = workInfo[1];
				nowMinute = workInfo[2];
			}
			else if (scores.Count == 0) continue;
			else
			{
				nowScore = Convert.ToInt32(scores.Pop());
				nowMinute = Convert.ToInt32(workMinutes.Pop());
			}

			if (nowMinute == 1)
				totalScore += nowScore;
			else
			{
				scores.Push(nowScore);
				workMinutes.Push(nowMinute - 1);
			}
		}
		Console.Write(totalScore);
	}

	static int[] ReadInt()
	{
		string[] strArr = Console.ReadLine().Split();
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
}