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
			//// 로직을 바로 이해하기 힘들어요. 'word의 i번째 문자가 이미 alphabets에 포함되어 있는가'라는 의도가 변수명에서 표현되지 않는 것 같습니다.
			//// alphabets 대신 accumulatedChars이라는 이름으로 한글자씩 누적해서 비교하고 있다는걸 표현한다던가
			//// isAlreadyExists라는 boolean 변수로 alphabets에 word[i]가 있는지 없는지를 표현한다던가
			//// 이런식으로 좀 더 예은님의 의도가 변수명, 함수명에서 잘 드러나면 더 읽기 쉬운 코드가 될 것 같습니다
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