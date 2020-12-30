using System;

public class Test
{
	public static void Main()
	{
		string input;
		string[] arr = new string[4];
		int max = 0;
		//// max_n이라는 이름으로는 어떤 역할을 하는 변수인지 예측하기가 힘들 것 같아요
		int max_n = 0;

		//// 전체적으로 깔끔하게 짜신 거 같아요
		for (int i = 0; i < 5; i++)
		{
			int sum = 0;
			input = Console.ReadLine();
			arr = input.Split(' ');
			for (int j = 0; j < 4; j++)
			{
				sum += Convert.ToInt32(arr[j]);
			}
			if (sum > max)
			{
				max = sum;
				max_n = i + 1;
			}

		}
		Console.WriteLine("{0} {1}", max_n, max);
	}
}