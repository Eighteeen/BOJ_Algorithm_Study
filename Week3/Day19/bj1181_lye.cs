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

	//// read_strings가 적절했을 것 같습니다
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