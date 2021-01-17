using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		string word = Console.ReadLine().ToUpper();
		int len = word.Length;
		string alphabets = "";
		int[] findCnt = new int[len];
		int cnt = 0, findIndex, max, res = 0;

		for (int i = 0; i < len; i++)
		{
			//// 로직을 바로 이해하기 힘들었어요. findIndex라는 이름으로는 'word의 i번째 문자가 이미 alphabets에 포함되어 있는가'라는 의도를 전혀 표현하지 못하는 것 같아요.
			findIndex = alphabets.IndexOf(word[i]);
			if (findIndex < 0)
			{
				alphabets += word[i];
				findIndex = alphabets.IndexOf(word[i]);
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

		Console.Write(cnt == 1 ? alphabets[res] : '?');
	}
}