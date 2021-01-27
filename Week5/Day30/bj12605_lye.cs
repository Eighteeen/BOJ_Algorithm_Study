using System;
using System.Collections;
using System.Text;

//// 깔끔효율 : 22
public class Test
{
	public static void Main()
	{
		int caseCnt = Convert.ToInt32(Console.ReadLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= caseCnt; i++)
		{
			string[] input = ReadString();
			int len = input.Length;
			Stack stack = new Stack();
			sb.Append("Case #" + i + ":");

			//// 여기 for문은 출력과 상관없이 스택을 구성하는 코드라서 위에 Case문이 있는 게 좀 어색하게 느껴져요. 출력과 함께 쓰여있다면 걸리는 거 없이 쭉 읽힐 것 같아요!
			for (int j = 0; j < len; j++)
				stack.Push(input[j]);

			for (int j = 0; j < len; j++)
				sb.Append(" " + stack.Pop());

			sb.Append("\n");
		}
		Console.Write(sb);
	}
	static string[] ReadString()
	{
		string[] strArr = Console.ReadLine().Split(' ');
		return strArr;
	}
}