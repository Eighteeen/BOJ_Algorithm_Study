using System;
using System.Collections;

//// 스택 활용 깔끔하네요
public class Test
{
	public static void Main()
	{
		int jul = Read_int();
		int num;
		int sum = 0;
		Stack st = new Stack();

		for (int i = 0; i < jul; i++)
		{
			num = Read_int();
			//// 나중에 계산하지 않고 입력받을때마다 빼고 더하는 방법도 있었네요. 배워갑니다!
			if (num == 0)
			{
				sum -= Convert.ToInt32(st.Peek());
				st.Pop();
			}
			else
			{
				st.Push(num);
				sum += num;
			}
		}
		Console.WriteLine(sum);
	}

	public static int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}