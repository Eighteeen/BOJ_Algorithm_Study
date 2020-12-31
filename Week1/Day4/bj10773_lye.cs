using System;
using System.Collections;

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