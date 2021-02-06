using System;
using System.Linq;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int lineCnt = Convert.ToInt32(Console.ReadLine());
		string[] sentences = ReadLines(lineCnt);
		Parrot pr = new Parrot();

		if (pr.IsDistinct())
		{
			Console.Write("Impossible");
			return;
		}
		for (int i = 0; i < lineCnt; i++)
		{
			pr.SetNowArr(sentences[i]);
			if (!pr.CheckCorrectSentence())
			{
				Console.Write("Impossible");
				return;
			}
		}

		if (pr.NowSize() == 0)
			Console.Write("Possible");
		else
			Console.Write("Impossible");
	}
	static string[] ReadLines(int n)
	{
		string[] lines = new string[n];
		for (int i = 0; i < n; i++)
		{
			lines[i] = Console.ReadLine();
		}
		return lines;
	}

}
public class Parrot
{
	List<string> written;
	string[] nowArr;

	public Parrot()
	{
		written = new List<string>(Console.ReadLine().Split(' '));
	}
	public int NowSize()
	{
		return written.Count;
	}
	public void SetNowArr(string now)
	{
		nowArr = now.Split(' ');
	}
	public bool IsDistinct()
	{
		int originCnt = written.Count;
		int distinctCnt = written.Distinct().ToArray().Length;
		return !(originCnt == distinctCnt);
	}
	public bool CheckCorrectSentence()
	{
		int nowLen = nowArr.Length - 1;
		int nowIndex, beforeIndex = nowLen + 1;

		for (int i = nowLen; i >= 0; i--)
		{
			nowIndex = written.IndexOf(nowArr[i]);
			if (nowIndex != -1) written.RemoveAt(nowIndex);
			if (i != nowLen && nowIndex >= beforeIndex) return false;
			beforeIndex = nowIndex;
		}
		return true;
	}
}