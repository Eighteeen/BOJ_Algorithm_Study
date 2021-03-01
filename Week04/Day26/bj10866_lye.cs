using System;
using System.Reflection;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Convert.ToInt32(Console.ReadLine());
		Dequeue deque = new Dequeue(line);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line; i++)
		{
			string[] input = Read_ints();
			switch (input[0])
			{
				case "push_front":
					deque.push_front(input[1]);
					break;

				case "push_back":
					deque.push_back(input[1]);
					break;

				case "pop_front":
					sb.Append(deque.frontWork(true));
					break;

				case "pop_back":
					sb.Append(deque.backWork(true));
					break;

				case "front":
					sb.Append(deque.frontWork(false));
					break;

				case "back":
					sb.Append(deque.backWork(false));
					break;

				default:
					sb.Append(deque.GetType().GetMethod(input[0]).Invoke(deque, null));
					break;
			}
		}
		Console.Write(sb);
	}
	static string[] Read_ints()
	{
		string[] strArr = Console.ReadLine().Split();
		return strArr;
	}

}

public class Dequeue
{
	string[] frontArr;
	string[] backArr;

	public Dequeue(int cnt)
	{
		frontArr = new string[cnt];
		backArr = new string[cnt];
	}

	int front = -1, back = -1;
	int frontInBack = 0, backInFront = 0;

	public void push_front(string input)
	{
		front++;
		frontArr[front] = input;
	}
	public void push_back(string input)
	{
		back++;
		backArr[back] = input;
	}
	public string frontWork(bool isPop)
	{
		if (front < backInFront)
		{
			if (back < frontInBack)
				return "-1\n";
			else
			{
				string res = backArr[frontInBack] + "\n";
				if (isPop)
				{
					frontInBack++;
				}
				return res;
			}
		}
		else
		{
			string res = frontArr[front] + "\n";
			if (isPop) front--;
			return res;
		}

	}
	public string backWork(bool isPop)
	{
		if (back < frontInBack)
		{
			if (front < backInFront)
				return "-1\n";
			else
			{
				string res = frontArr[backInFront] + "\n";
				if (isPop)
				{
					backInFront++;
				}
				return res;
			}
		}
		else
		{
			string res = backArr[back] + "\n";
			if (isPop) back--;
			return res;
		}
	}
	public string size()
	{
		int backSize = back - frontInBack + 1;
		int frontSize = front - backInFront + 1;
		return backSize + frontSize + "\n";

	}
	public string empty()
	{
		if (back < frontInBack && front < backInFront)
			return "1\n";
		else
			return "0\n";
	}

}