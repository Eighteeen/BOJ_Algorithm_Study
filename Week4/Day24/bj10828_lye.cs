using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Convert.ToInt32(Console.ReadLine());
		string[] stack = new string[line];
		int top = -1;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line; i++)
		{
			string[] input = Read_ints();

			if (input[0] == "push")
			{
				top++;
				stack[top] = input[1];
			}

			if (input[0] == "pop")
			{
				if (top == -1)
					sb.Append("-1\n");
				else
				{
					sb.Append(stack[top] + "\n");
					top--;
				}
			}

			if (input[0] == "top")
			{
				if (top == -1)
					sb.Append("-1\n");
				else
					sb.Append(stack[top] + "\n");
			}

			if (input[0] == "size")
				sb.Append(top + 1 + "\n");

			if (input[0] == "empty")
				sb.Append(top == -1 ? "1\n" : "0\n");
		}

		Console.Write(sb);
	}
	static string[] Read_ints()
	{
		string[] strArr = Console.ReadLine().Split();
		return strArr;
	}
}