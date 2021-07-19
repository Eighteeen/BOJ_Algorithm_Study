using System;

public class Test
{
	public static void Main()
	{
		//// 굳이 선언과 할당을 따로 할 필요 없다고 생각해요.
		string input;
		int total;
		int sum = 0;

		//// input 변수를 따로 두니까 가독성이 조금 떨어지는 것 같아요. ReadLine과 ToInt32까지 해주는 함수를 만들어 쓰는건 어떨까요?
		input = Console.ReadLine();
		total = Convert.ToInt32(input);

		for (int i = 0; i < 9; i++)
		{
			input = Console.ReadLine();
			sum += Convert.ToInt32(input);
		}

		Console.WriteLine(total - sum);
	}
}