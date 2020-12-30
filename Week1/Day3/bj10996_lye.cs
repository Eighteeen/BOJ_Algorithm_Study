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
			//// 개행문자 바로 뒤에 띄어쓰기가 있어서 코드를 보고 ????? 어떻게 작동되는 거지?? 하고 당황해서 개인적으로 이부분이 조금 아쉬웠어요
			ts.Print_star(jul);
			Console.WriteLine();
		}

	}

	public void Print_star(int n)
	{
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