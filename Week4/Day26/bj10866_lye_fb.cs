using System;
using System.Reflection;
using System.Text;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int line = Convert.ToInt32(Console.ReadLine());
		Dequeue deque = new Dequeue();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line; i++)
		{
			string[] input = ReadInt();
			if (input[0] == "push_front")
				deque.push_front(input[1]);
			else if (input[0] == "push_back")
				deque.push_back(input[1]);
			else
				sb.Append(deque.GetType().GetMethod(input[0]).Invoke(deque, null));
		}
		Console.Write(sb);
	}
	static string[] ReadInt()
	{
		string[] strArr = Console.ReadLine().Split(' ');
		return strArr;
	}

}

public class Dequeue
{
	List<string> dequeue = new List<string>();
	int frontIndex = 0;

	public void push_front(string input)
	{
		dequeue.Insert(frontIndex, input);
	}
	public void push_back(string input)
	{
		dequeue.Add(input);
	}
	public string pop_front()
	{
		if (dequeue.Count == 0)
			return "-1\n";
		else
		{
			string res = dequeue[frontIndex] + "\n";
			dequeue.RemoveAt(frontIndex);
			return res;
		}
	}
	public string pop_back()
	{
		if (dequeue.Count == 0)
			return "-1\n";
		else
		{
			int index = dequeue.Count - 1;
			string res = dequeue[index] + "\n";
			dequeue.RemoveAt(index);
			return res;
		}

	}
	public string size()
	{
		return dequeue.Count + "\n";
	}
	public string empty()
	{
		if (dequeue.Count == 0)
			return "1\n";
		else
			return "0\n";
	}
	public string front()
	{
		if (dequeue.Count == 0)
			return "-1\n";
		else
		{
			return dequeue[frontIndex] + "\n";
		}
	}
	public string back()
	{
		if (dequeue.Count == 0)
			return "-1\n";
		else
		{
			int index = dequeue.Count - 1;
			return dequeue[index] + "\n";
		}
	}

}