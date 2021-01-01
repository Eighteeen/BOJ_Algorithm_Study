using System;

public class Test
{
	public static void Main()
	{
		int[] arr = new int[31];
		int num;

		//// 28명만 과제를 제출한 경우에만 미제출자를 구할 수 있는 코드라서 아쉬워요
		for (int i = 0; i < 28; i++)
		{
			num = Read_int();
			arr[num] = num;
		}

		for (int i = 1; i < 31; i++)
		{
			if (arr[i] == 0)
				Console.WriteLine(i);
		}
	}

	public static int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}