using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Test
{
	public static void Main()
	{
		int wordCnt = Convert.ToInt32(Console.ReadLine());
		string[] words = read_string(wordCnt);
		int[] wordLengths = get_lengths(words);
		int min = wordLengths.Min();
		List<string> sortedWords = new List<string>();
		StringBuilder sb = new StringBuilder();

		//// 길이가 50 이하인걸 이용한 방법 좋네용
		while (min != 51)
		{
			List<string> minWords = new List<string>();

			for (int i = 0; i < words.Length; i++)
			{
				if (wordLengths[i] == min)
				{
					minWords.Add(words[i]);
					//// 개인적으로 이부분에서 왜 51을 저장해주는지 알기 어려워 아쉬웠습니다.
					//// 더이상 min 검사를 원치 않는다거나 탐색이 끝났다라는 의미의 변수가 있었으면 가독성면에서 좋을 것 같아요
					////-> 하나가 아니라 여러개를 찾아야 하는것이기 때문에 여기서 검색을 끝낼 수는 없어요 그래서 다음 min에 걸리지 않도록
					////-> 하기 위해서 만들어준 부분이에요. 길이 최대값이 50이기 때문에 51을 저장했습니다.
					wordLengths[i] = 51;
				}
			}
			minWords.Sort();
			sortedWords.AddRange(minWords);
			min = wordLengths.Min();
		}

		foreach (string word in sortedWords.Distinct())
			sb.Append(word + "\n");
		Console.Write(sb);
	}

	//// read_strings가 적절했을 것 같습니다 : 22
	static string[] read_string(int n)
	{
		string[] strings = new string[n];
		for (int i = 0; i < n; i++)
			strings[i] = Console.ReadLine();
		return strings;
	}
	static int[] get_lengths(string[] strs)
	{
		int len = strs.Length;
		int[] lengths = new int[len];
		for (int i = 0; i < len; i++)
			lengths[i] = strs[i].Length;
		return lengths;
	}
}
