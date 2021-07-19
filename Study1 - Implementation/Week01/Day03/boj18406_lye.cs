using System;

//// 깔끔하게 잘 짜신거 같아요!
public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		int len = input.Length;
		//// 1/2보다 left/right, front/back로 지었다면 더 직관적이었을 거 같아요
		int sum1 = 0, sum2 = 0;

		//// len / 2 값을 저장하고 쓰는 게 효율성에 더 좋을 것 같아요. : 22
		for (int i = 0; i < len / 2; i++)
		{
			sum1 += Convert.ToInt32(input[i]);
			sum2 += Convert.ToInt32(input[len / 2 + i]);
		}

		if (sum1 == sum2) Console.WriteLine("LUCKY");
		else Console.WriteLine("READY");
	}
}