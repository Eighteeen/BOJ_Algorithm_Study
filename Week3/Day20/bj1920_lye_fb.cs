using System;
using System.Text;
using System.Linq;


public class Test
{
	public static void Main()
	{
		Console.ReadLine();
		int[] compare = Read_ints();
		int inputCnt = Convert.ToInt32(Console.ReadLine());
		int[] input = Read_ints();
		bool[] isExist = new bool[inputCnt];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < inputCnt; i++)
		{
			if (compare.Contains(input[i]))
				isExist[i] = true;

			sb.Append(Convert.ToInt32(isExist[i]) + "\n");
		}
		Console.Write(sb);
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