using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int[] balls = Read_ints();
		int boxCnt = 0, packed;

		for (int i = 0; i < balls.Length; i++)
		{
			packed = Packing_OneColor(balls[i]);
			balls[i] -= packed * 3;
			boxCnt += packed;
		}
		
		packed = Packing_Colorful(balls[0], balls[1], balls[2]);
		boxCnt += packed;
		int left = balls.Sum();
		left -= packed * 3;

		if (left != 0)
			boxCnt += left > 2 ? 2 : 1;

		Console.Write(boxCnt);
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
	static int Packing_OneColor(int ball)
	{
		int cnt = 0;
		if (ball > 2)
		{
			cnt = ball / 3;
		}
		return cnt;
	}
	static int Packing_Colorful(int r, int g, int b)
	{
		int cnt = 0;
		for (int i = 0; i < 2; i++)
		{
			if ((r > 0) && (g > 0) && (b > 0))
			{
				cnt++;
				r--;
				g--;
				b--;
			}
		}
		return cnt;
	}
}