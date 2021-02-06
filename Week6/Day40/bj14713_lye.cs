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

		//// 아래 if문을 통째로 지워도 "맞았습니다"로 뜹니다. 문제의 4번 조건이 있어 중복 단어가 들어올 가능성이 없기 때문입니다.
		if (pr.IsDistinct())
		{
			Console.Write("Impossible");
			return;
		}
		for (int i = 0; i < lineCnt; i++)
		{
			//// '앵무새'로 추상화한데에 비해 함수 이름은 SetNowArr로 "현재의 배열을 설정"이라는 매우 구체적인 구현을 표현하고 있습니다.
			//// 함수 이름을 '전할 메시지 설정' 등으로 추상화 했다면 더 직관적이었을 것 같아요
			pr.SetNowArr(sentences[i]);
			//// 문제를 푸신 방식은 참신한데 스택/큐/덱 중 어느 자료구조도 쓰지 않은 점이 아쉽네요 ㅠㅠ
			//// 스택/큐/덱을 활용하는 방법을 익히는 것이 이번 주의 주제니 세 자료구조 중 하나는 썼어야 됐을 것 같아요
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
	//// 유일한가? 라는 함수이름과 다르게 중복인가?에 대한 결과를 반환하네요
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