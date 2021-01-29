using System;
using System.Collections;

//// 함수 작명이 대체로 추상적이어서 이게 어떤 일을 하는 함수일지 이름만으론 유추하기 힘든 부분이 있어요 ㅠㅠ
//// 예은님의 의도가 더 잘 드러나는 함수명을 더 고민해보시면 좋을 것 같아요!
public class Test
{
	public static void Main()
	{
		int numCnt = Convert.ToInt32(Console.ReadLine());
		string postfix = Console.ReadLine();
		//// value는 너무 포괄적인 것 같아요! numberArr나 numArr는 어때요?
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

	//// 함수활용 조씁니다
	static bool IsAlphabet(char N)
	{
		//// 조건식을 그대로 return하면 if문과 else문이 필요 없어요!
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