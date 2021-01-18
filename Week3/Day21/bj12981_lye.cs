using System;

public class Test
{
	public static void Main()
	{
		int[] balls = Read_ints();
		int red = balls[0];
		int green = balls[1];
		int blue = balls[2];
		int boxCnt = 0, packed, left;

		//// 같은 코드가 14~16줄, 18~20줄, 22~24줄로 반복됩니다.
		//// -> 혹시 좋은 방법 있을까요 오히려 함수로 묶으려고 하면 더 복잡해질거 같아서 이렇게 짰어요ㅠ
		packed = Packing_OneColor(red);
		red -= packed * 3;
		boxCnt += packed;

		packed = Packing_OneColor(green);
		green -= packed * 3;
		boxCnt += packed;

		packed = Packing_OneColor(blue);
		blue -= packed * 3;
		boxCnt += packed;

		packed = Packing_Colorful(red, green, blue);
		boxCnt += packed;
		red -= packed;
		green -= packed;
		blue -= packed;

		left = red + green + blue;
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
