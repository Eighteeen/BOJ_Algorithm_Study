using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int numCnt = Convert.ToInt32(Console.ReadLine());
		string postfix = Console.ReadLine();
		int[] valueArr = FillArray(numCnt);
		Stack stack = new Stack();

		for (int i = 0; i < postfix.Length; i++)
		{
			if (IsAlphabet(postfix[i]))
				stack.Push(FindNumValue(valueArr, postfix[i]));

			else
				stack.Push(Calc(stack, postfix[i]));
		}
		Console.Write("{0:0.00}", Convert.ToDouble(stack.Pop()));
	}

	static int[] FillArray(int n)
	{
		int[] numArr = new int[n];
		for (int i = 0; i < n; i++)
			numArr[i] = Convert.ToInt32(Console.ReadLine());
		return numArr;
	}

	static int FindNumValue(int[] numArr, char N)
	{
		int index = N - 'A';
		return numArr[index];
	}

	static bool IsAlphabet(char N)
	{
		if (N >= 'A' && N <= 'Z')
			return true;
		else
			return false;
	}

	static double Calc(Stack stack, char N)
	{
		double sec = Convert.ToDouble(stack.Pop());
		double fir = Convert.ToDouble(stack.Pop());

		if (N == '+')
			return fir + sec;

		else if (N == '-')
			return fir - sec;

		else if (N == '*')
			return fir * sec;

		return fir / sec;
	}
}