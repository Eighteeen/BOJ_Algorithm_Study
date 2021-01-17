using System;

public class Test
{
	public static void Main()
	{
		string firstNum = Console.ReadLine();
		string secondNum = Console.ReadLine();
		string mergeNum = mergeNums(firstNum, secondNum);
		int len = firstNum.Length + secondNum.Length - 2;

		for (int i = 0; i < len; i++)
		{
			mergeNum = sumEachIndex(mergeNum);
		}

		Console.Write(mergeNum);

	}

	static string mergeNums(string a, string b)
	{
		string merge = "";
		for (int i = 0; i < a.Length; i++)
		{
			merge += a[i] + b[i};
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
