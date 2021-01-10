using System;

public class Test
{
	public static void Main()
	{
		int[] lines = Read_numArr();
		int row = lines[0];
		int col = lines[1];
		int rowmax = 0, colmax = 0, cnt = 0, allcnt = 0, nineCntCheck;
		int[,] bingo = new int[row, col];


		for (int i = 0; i < row; i++)
		{
			int[] toAdd = Read_numArr();
			cnt = 0;
			for (int j = 0; j < col; j++)
			{
				nineCntCheck = howManyNine(toAdd[j]);
				if (nineCntCheck > 0)
				{
					cnt += nineCntCheck;
					allcnt += nineCntCheck;
				}
				bingo[i, j] = toAdd[j];
			}
			if (cnt > rowmax)
				rowmax = cnt;
		}

		for (int i = 0; i < col; i++)
		{
			cnt = 0;

			for (int j = 0; j < row; j++)
			{
				nineCntCheck = howManyNine(bingo[j, i]);
				if (nineCntCheck > 0)
				{
					cnt += nineCntCheck;
				}
			}
			if (cnt > colmax)
				colmax = cnt;
		}

		Console.Write(rowmax > colmax ? allcnt - rowmax : allcnt - colmax);

	}

	static int[] Read_numArr()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
	static int howManyNine(int num)
	{
		string check = num.ToString();
		int cnt = 0;
		for (int i = 0; i < check.Length; i++)
		{
			if (check[i] == '9') { cnt++; }
		}
		return cnt;
	}
}