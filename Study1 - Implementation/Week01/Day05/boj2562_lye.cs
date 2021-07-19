using System;

//// 굳 10초만에 읽혔습니다
//// 깔끔하고 알아보기 쉽게 하신거 같아요. : 22
public class Test
{
	public static void Main()
	{
		int max = 0;
		int max_index = 0;
		int num;

		for (int i = 0; i < 9; i++)
		{
			num = Read_int();
			if (num > max)
			{
				max = num;
				max_index = i + 1;
			}
		}
		Console.Write(max + "\n" + max_index);
	}

	static int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}