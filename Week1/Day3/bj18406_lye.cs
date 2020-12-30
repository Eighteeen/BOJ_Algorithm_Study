using System;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		int len = input.Length;
		int sum1 = 0, sum2 = 0;

		//// len / 2 값을 저장하고 쓰는 게 효율성에 더 좋을 것 같아요.
		for (int i = 0; i < len / 2; i++)
		{
			sum1 += Convert.ToInt32(input[i]);
			sum2 += Convert.ToInt32(input[len / 2 + i]);
		}

		if (sum1 == sum2) Console.WriteLine("LUCKY");
		else Console.WriteLine("READY");
	}
}