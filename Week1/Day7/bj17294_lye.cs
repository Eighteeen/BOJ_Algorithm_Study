using System;

public class Test
{
	////깔끔하네요
	public static void Main()
	{
		int[] numbers = Read_ints();
		int len = numbers.Length;
		//// || len == 2를 빼도 정답으로 처리됩니다!
		if (len == 1 || len == 2)
		{
			Console.Write("◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!");
			return;
		}
		int gap = numbers[1] - numbers[0];
		for (int i = 0; i < len - 1; i++)
		{
			if (numbers[i + 1] - numbers[i] != gap)
			{
				Console.Write("흥칫뿡!! <(￣ ﹌ ￣)>");
				return;
			}
		}
		Console.Write("◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!");
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		int[] numArr = new int[input.Length];
		for (int i = 0; i < input.Length; i++)
			numArr[i] = Convert.ToInt32(input[i]);
		return numArr;
	}
}