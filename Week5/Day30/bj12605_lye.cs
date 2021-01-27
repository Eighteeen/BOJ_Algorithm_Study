using System;
using System.Collections;
using System.Text;

//// 깔끔효율
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