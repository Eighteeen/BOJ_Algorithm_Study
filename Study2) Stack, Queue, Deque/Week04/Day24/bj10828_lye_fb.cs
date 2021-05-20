using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Convert.ToInt32(Console.ReadLine());
		Stack stack = new Stack(line);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line; i++)
		{
			string[] input = ReadInt();

			if (input[0] == "push")
				stack.push(input[1]);
			else
				sb.Append(stack.GetType().GetMethod(input[0]).Invoke(stack, null));
		}

		Console.Write(sb);
	}
	static string[] ReadInt()
	{
		string[] strArr = Console.ReadLine().Split();
		return strArr;
	}
}
public class Stack
{
	string[] stack;
	int topIndex = -1;
	public Stack(int size)
	{
		stack = new string[size];
	}
	public void push(string input)
	{
		topIndex++;
		stack[topIndex] = input;
	}
	public string pop()
	{
		if (topIndex == -1)
			return "-1\n";
		else
			return stack[topIndex--] + "\n";
	}
	public string top()
	{
		if (topIndex == -1)
			return "-1\n";
		else
			return stack[topIndex] + "\n";
	}
	public string size()
	{
		return topIndex + 1 + "\n";
	}
	public string empty()
	{
		return topIndex == -1 ? "1\n" : "0\n";
	}
}