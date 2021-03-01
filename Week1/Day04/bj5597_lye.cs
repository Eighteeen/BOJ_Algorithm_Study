using System;

public class Test
{
	public static void Main()
	{
		int[] arr = new int[31];
		int num;

		//// 28명만 과제를 제출한 경우에만 미제출자를 구할 수 있는 코드라서 아쉬워요
		//// 오잉 전 다른 의견입니다. 문제에서 입력 개수를 정해둔거라 28개만 입력받는게 맞다고 생각해요.
		for (int i = 0; i < 28; i++)
		{
			num = Read_int();
			//// 숫자가 0이냐 0이 아니냐로만 판단한다면 boolean 배열을 사용하는게 좋았을 것 같습니다!
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