using System;
using System.Collections;

//// 전체적으로 깔끔합니다 :22
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
	//// 의도도 분명하고 해당 함수 활용이 좋네요
	static bool IsNum(char ch)
	{
		return (ch >= '0' && ch <= '9');
	}
	//// stack이 아니라 피연산자 자체를 받는 함수였다면 stack뿐만 아니라 여러 방면에서 쓰일 수 있는 함수가 될 수 있을 것 같습니다 :22 동감입니당
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