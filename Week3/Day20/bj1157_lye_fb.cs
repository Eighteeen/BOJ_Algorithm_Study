using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		string word = Console.ReadLine().ToUpper();
		int len = word.Length;
		string accumulatedChars = "";
		int[] findCnt = new int[26];
		int cnt = 0, findIndex, max, res = 0;

		for (int i = 0; i < len; i++)
		{
			findIndex = accumulatedChars.IndexOf(word[i]);
			if (findIndex < 0)
			{
				accumulatedChars += word[i];
				findIndex = accumulatedChars.IndexOf(word[i]);
			}
			findCnt[findIndex]++;
		}
		max = findCnt.Max();
		for (int i = 0; i < len; i++)
		{
			if (max == findCnt[i])
			{
				cnt++;
				res = i;
			}
		}

		Console.Write(cnt == 1 ? accumulatedChars[res] : '?');
	}
}