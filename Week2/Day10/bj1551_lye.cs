using System;
using System.Text;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int[] lengthRepeat = Read_ints(false);
		int length = lengthRepeat[0];
		int repeat = lengthRepeat[1];
		int[] Sequence = Read_ints(true);
		StringBuilder sb = new StringBuilder();

		//// 출력을 한번에 하느라 예외처리를 하셨군요 이 방법도 괜찮아보입니다!
		if (repeat == 0 || length == 1)
		{
			for (int i = 0; i < length; i++)
			{
				sb.Append(Sequence[i] + (i == length - 1 ? "" : ","));
			}
			Console.Write(sb);
			return;
		}

		for (int i = 0; i < repeat; i++)
		{
			length--;
			for (int j = 0; j < length; j++)
			{
				Sequence[j] = Sequence[j + 1] - Sequence[j];
				if (i == repeat - 1) sb.Append(Sequence[j] + (j == length - 1 ? "" : ","));
			}
		}
		Console.Write(sb);
	}

	static int[] Read_ints(bool isSeq)
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(isSeq ? ',' : ' ');
		int len = strArr.Length;
		int[] intArr = new int[len];
		for (int i = 0; i < len; i++)
			intArr[i] = Convert.ToInt32(strArr[i]);
		return intArr;
	}

}
