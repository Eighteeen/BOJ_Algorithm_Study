using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		int jul;
		jul = ts.Read_int();

		for (int i = 0; i < jul; i++)
		{
			ts.Print_star(jul + 1);
			Console.Write("\n ");
			//// 개행문자 바로 뒤에 띄어쓰기가 있어서 코드를 보고 ????? 어떻게 작동되는 거지?? 하고 당황해서 개인적으로 이부분이 조금 아쉬웠어요 :22
			////-> 두번째줄엔 항상 젤 첫칸이 띄어져 있어서 그부분을 표현한거에요
			ts.Print_star(jul);
			Console.WriteLine();
		}

	}

	public void Print_star(int n)
	{
		//// "* "를 통째로 출력하는 방법 배워갑니다!
		for (int i = 0; i < n / 2; i++)
		{
			Console.Write("* ");
		}
	}
	public int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}
