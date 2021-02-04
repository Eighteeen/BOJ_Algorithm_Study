using System;
using System.Collections;
using System.Text;

public class Test
{
	public static void Main()
	{
		int arrCnt = Convert.ToInt32(Console.ReadLine());
		int[] numArr = ReadInt(arrCnt);
		int[] resArr = new int[arrCnt];
		Stack stack = new Stack();

		for (int i = 0; i < arrCnt; i++)
		{
			if (stack.Count == 0)
			{
				stack.Push(i);
				continue;
			}

			int top = Convert.ToInt32(stack.Peek());

			while (stack.Count != 0 && numArr[top] < numArr[i])
			{
				resArr[top] = numArr[i];
				stack.Pop();
				if (stack.Count != 0) top = Convert.ToInt32(stack.Peek());
			}
			stack.Push(i);
		}

		while (stack.Count != 0)
		{
			int top = Convert.ToInt32(stack.Pop());
			resArr[top] = -1;
		}

		PrintArr(resArr, arrCnt);
	}
	static int[] ReadInt(int cnt)
	{
		string[] input = Console.ReadLine().Split(' ');
		int[] numArr = new int[cnt];
		for (int i = 0; i < cnt; i++)
		{
			numArr[i] = Convert.ToInt32(input[i]);
		}
		return numArr;
	}
	static void PrintArr(int[] resArr, int arrCnt)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arrCnt; i++)
		{
			sb.Append(resArr[i]).Append(" ");
		}
		Console.Write(sb);
	}
}