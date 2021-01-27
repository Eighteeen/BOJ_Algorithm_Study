using System;
using System.Text;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int caseCnt = ReadInt();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < caseCnt; i++)
		{
			string commands = Console.ReadLine();
			int size = ReadInt();
			Dequeue deque = new Dequeue(size);
			InsertValue(deque, size);
			bool reverse = false;
			bool isBreak = false;

			for (int j = 0; j < commands.Length; j++)
			{
				if (commands[j] == 'R') reverse = !reverse;
				if (commands[j] == 'D')
				{
					if (deque.size() == 0)
					{
						sb.Append("error\n");
						isBreak = true;
						break;
					}
					if (reverse)
						deque.pop_back();
					else
						deque.pop_front();
				}
			}
			if (isBreak == false)
			{
				sb.Append("[");
				int limit = deque.size();
				for (int k = 0; k < limit; k++)
				{
					if (reverse)
						sb.Append(deque.pop_back());
					else
						sb.Append(deque.pop_front());
					if (k != limit - 1) sb.Append(",");
				}
				sb.Append("]\n");
			}

		}
		Console.Write(sb);
	}
	static int ReadInt()
	{
		return Convert.ToInt32(Console.ReadLine());
	}
	static void InsertValue(Dequeue deque, int n)
	{
		string[] strArr = Console.ReadLine().Replace("[", "").Replace("]", "").Split(',');
		for (int i = 0; i < n; i++)
		{
			deque.push_back(Convert.ToInt32(strArr[i]));
		}
	}
}
public class Dequeue
{
	int[] dequeue;
	int front = 0, back = 0, dequeSize;

	public Dequeue(int n)
	{
		dequeue = new int[n + 1];
		dequeSize = n + 1;
	}

	public void push_front(int input)
	{
		dequeue[front] = input;
		front = (dequeSize + front - 1) % dequeSize;
	}
	public void push_back(int input)
	{
		back = (back + 1) % dequeSize;
		dequeue[back] = input;
	}
	public int pop_front()
	{
		front = (dequeSize + front + 1) % dequeSize;
		return dequeue[front];
	}
	public int pop_back()
	{
		int res = dequeue[back];
		back = (dequeSize + back - 1) % dequeSize;
		return res;
	}
	public int size()
	{
		int res;
		if (back < front)
		{
			int backSize = dequeSize + back;
			int frontSize = dequeSize - front;
			res = backSize + frontSize;
		}
		else
		{
			res = back - front;

		}

		return res;
	}

}
