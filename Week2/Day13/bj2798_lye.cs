using System;

public class Test
{
	////깔끔하네요!
	public static void Main()
	{
		int sum, gap, result = 0;
		int[] info = Read_ints();
		int len = info[0];
		int goal = info[1];
		int min = 100000;
		int[] numArr = Read_ints();

		for (int i = 0; i < len - 2; i++)
		{
			for (int j = i + 1; j < len - 1; j++)
			{
				for (int k = j + 1; k < len; k++)
				{
					sum = numArr[i] + numArr[j] + numArr[k];
					gap = goal - sum;
					if ((gap > 0 || gap == 0) && gap < min)
					{
						min = gap;
						result = sum;
					}


				}
			}
		}
		Console.Write(result);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int len = strArr.Length;
		int[] nums = new int[len];
		for (int i = 0; i < len; i++)
			nums[i] = Convert.ToInt32(strArr[i]);
		return nums;
	}
}