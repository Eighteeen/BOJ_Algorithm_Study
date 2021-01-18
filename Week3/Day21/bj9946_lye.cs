using System;
using System.Text;

//// 무난한거같습니다
public class Test
{
	public static void Main()
	{
		string completeWord, returnWord, result;
		int cnt = 1;
		StringBuilder sb = new StringBuilder();

		while (true)
		{
			completeWord = Console.ReadLine();
			returnWord = Console.ReadLine();

			if (completeWord.Length != returnWord.Length)
			{
				sb.Append("Case " + cnt + ": different\n");
				cnt++;
				continue;
			}

			result = Compare(completeWord, returnWord);
			if (result == "same" && returnWord == "END") break;

			sb.Append("Case " + cnt + ": ")
				.Append(result + "\n");
			cnt++;
		}
		Console.Write(sb);
	}
	static string Compare(string word1, string word2)
	{
		int len = word1.Length;
		int cnt = 0, findIndex;
		char[] comp = new char[len];
		for (int i = 0; i < len; i++)
		{
			comp[i] = word1[i];
		}
		for (int i = 0; i < len; i++)
		{
			findIndex = Array.IndexOf(comp, word2[i]);
			if (findIndex > -1)
			{
				cnt++;
				comp[findIndex] = ' ';
			}

		}
		return cnt == len ? "same" : "different";
	}
}