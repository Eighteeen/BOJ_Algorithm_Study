using System;
using System.Collections.Generic;
using System.Text;

public class Test
{
	public static void Main()
	{
		int[] input = ReadInt();
		int len = input[0];
		int findIndex = input[1];
		int reverseIndex = input[2];
		int reverseCnt = 0, findCnt = 0;
		bool reverse = false;
		Dequeue deque = new Dequeue(len);
		StringBuilder sb = new StringBuilder();

		SetRange(len, deque);

		while (deque.size() != 0)
		{
			if (reverse)
			{
				for (findCnt = 1; findCnt < findIndex; findCnt++)
				{
					deque.push_front(deque.pop_back());
				}
				sb.Append(deque.pop_back() + "\n");
			}
			else
			{
				for (findCnt = 1; findCnt < findIndex; findCnt++)
				{
					deque.push_back(deque.pop_front());
				}
				sb.Append(deque.pop_front() + "\n");
			}
			reverseCnt++;

			if (reverseCnt == reverseIndex)
			{
				reverse = !reverse;
				reverseCnt = 0;
			}
		}
		Console.Write(sb);
	}
	static int[] ReadInt()
	{
		string[] input = Console.ReadLine().Split(' ');
		int len = input.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(input[i]);
		return numArr;
	}
	static void SetRange(int n, Dequeue deque)
	{
		for (int i = 1; i <= n; i++)
		{
			deque.push_back(i);
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