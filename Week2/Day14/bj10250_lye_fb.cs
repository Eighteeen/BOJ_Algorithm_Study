using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int repeat = Read_int();
		int H, W, customerNum, cnt;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < repeat; i++)
		{
			int[] toFindInfo = Read_ints();
			H = toFindInfo[0] + 1;
			W = toFindInfo[1] + 1;
			customerNum = toFindInfo[2];
			cnt = 0;

			for (int j = 1; j < W; j++)
			{
				for (int k = 1; k < H; k++)
				{
					cnt++;
					if (cnt == customerNum)
					{
						sb.Append(k.ToString());
						sb.Append(j < 10 ? "0" : "");
						sb.Append(j.ToString() + "\n");
					}
				}
			}
		}
		Console.Write(sb);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split();
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
}