using System;

public class Test
{
	//// 적절한 이름의 함수로 잘 분리되어서 로직을 파악하기 쉬웠어요! : 22
	public static void Main()
	{
		string firstNum = Console.ReadLine();
		string secondNum = Console.ReadLine();
		string mergeNum = mergeNums(firstNum, secondNum);

		//// 반복횟수를 왜 14번 해야하는지 표현되었으면 더 좋았을 것 같습니다! : 22
		for (int i = 0; i < 14; i++)
		{
			mergeNum = sumEachIndex(mergeNum);
		}

		Console.Write(mergeNum);

	}

	static string mergeNums(string a, string b)
	{
		string merge = "";
		string sum = "";
		for (int i = 0; i < a.Length; i++)
		{
			//// sum 변수가 없어도 충분히 가독성 있었을 것 같아요! : 22
			sum = "";
			sum += a[i];
			sum += b[i];
			merge += sum;
		}
		return merge;
	}

	static string sumEachIndex(string num)
	{
		string res = "";
		int fir, sec, sum;
		for (int i = 0; i < num.Length - 1; i++)
		{
			fir = Convert.ToInt32(num[i]) - '0';
			sec = Convert.ToInt32(num[i + 1]) - '0';
			sum = (fir + sec) % 10;
			res += sum.ToString();
		}
		return res;
	}
}
