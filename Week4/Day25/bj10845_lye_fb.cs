using System;
using System.Text;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int line = Convert.ToInt32(Console.ReadLine());
		Queue queue = new Queue(line);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line; i++)
		{
			string[] input = ReadInt();
			if (input[0] == "push")
				queue.push(input[1]);
			else
				sb.Append(queue.GetType().GetMethod(input[0]).Invoke(queue, null));

		}
		Console.Write(sb);
	}
	static string[] ReadInt()
	{
		string[] strArr = Console.ReadLine().Split();
		return strArr;
	}
}
public class Queue
{
	string[] queue;
	int frontIndex = 0, backIndex = 0;
	int queueSize;

	public Queue(int size)
	{
		queue = new string[size];
		queueSize = size;
	}
	public void push(string input)
	{
		backIndex = (backIndex + 1) % queueSize;
		queue[backIndex] = input;
	}
	public string pop()
	{
		if (frontIndex == backIndex)
			return "-1\n";
		else
		{
			frontIndex = (frontIndex + 1) % queueSize;
			return queue[frontIndex] + "\n";
		}
	}
	public string size()
	{
		return Math.Abs(backIndex - frontIndex) + "\n";
	}
	public string empty()
	{
		if (frontIndex == backIndex)
			return "1\n";
		else
			return "0\n";
	}
	public string front()
	{
		if (frontIndex == backIndex)
			return "-1\n";
		else
			return queue[frontIndex + 1] + "\n";
	}
	public string back()
	{
		if (frontIndex == backIndex)
			return "-1\n";
		else
			return queue[backIndex] + "\n";
	}

}