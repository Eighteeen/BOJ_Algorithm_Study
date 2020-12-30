using System;

public class Test
{
	public static void Main()
	{
		string input;
		int num;
		while (true)
		{
			input = Console.ReadLine();
			//// '입력 값의 길이'를 담는 변수라 num보다는 len, length가 더 적절했을 것 같습니다 : 22
			num = input.Length;
			if (input == "END") break;
			for (int i = num; i > 0; i--)
			{
				//// 입출력문 사용이 잦아서 시간소비가 좀 있을 것 같습니다. StringBuilder로 모아뒀다가 한번에 출력하는건 어떨까요? : 22 현재처럼 출력을 많이 하는 문제는 필요로 보여요
				Console.Write(input[i - 1].ToString());
			}
			Console.Write("\n");
		}
	}
}