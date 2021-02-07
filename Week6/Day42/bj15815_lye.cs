using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		string input = Console.ReadLine();
		Stack st = new Stack();

		for (int i = 0; i < input.Length; i++)
		{
			if (IsNum(input[i]))
			{
				st.Push(input[i] - '0');
				continue;
			}
			st.Push(Calc(st, input[i]));
		}
		Console.Write(st.Pop());
	}
	static bool IsNum(char ch)
	{
		return (ch >= '0' && ch <= '9');
	}
	static int Calc(Stack st, char ch)
	{
		int second = Convert.ToInt32(st.Pop());
		int first = Convert.ToInt32(st.Pop());
		int res = 0;

		switch (ch)
		{
			case '+':
				res = first + second;
				break;

			case '-':
				res = first - second;
				break;

			case '*':
				res = first * second;
				break;

			case '/':
				res = first / second;
				break;
		}

		return res;
	}
}