using System;
using System.Collections.Generic;
using System.Text;

//// 효율적이고 읽을만합니당
//// 전체적으로 무난하게 잘 짜신 것 같습니다.
public class Test
{
	public static void Main()
	{
		int numCnt = Convert.ToInt32(Console.ReadLine());
		int[] skillArr = getNumArray();
		Dequeue deque = new Dequeue(numCnt);

		for (int i = numCnt - 1; i >= 0; i--)
		{
			//// 이 문제같은 경우에는 nowSkill의 경우 1, 2, 3에 따라서만 코드가 진행되니 switch case문이 읽고 이해하기에 좀 더 편할 것 같아요!
			int nowSkill = skillArr[i];
			if (nowSkill == 1)
			{
				deque.push_front(numCnt - i);
				continue;
			}
			else if (nowSkill == 2)
			{
				int nowPop = deque.pop_front();
				deque.push_front(numCnt - i);
				deque.push_front(nowPop);
				continue;
			}
			deque.push_back(numCnt - i);
		}
		deque.PrintNumbers();
	}
	static int[] getNumArray()
	{
		string[] input = Console.ReadLine().Split(' ');
		int len = input.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
		{
			numArr[i] = Convert.ToInt32(input[i]);
		}
		return numArr;
	}
}

public class Dequeue
{
	int[] dequeue;
	int front = 0, back = 0, dequeSize;
	StringBuilder sb = new StringBuilder();

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
	public void PrintNumbers()
	{
		for (int i = 0; i < dequeSize - 1; i++)
			sb.Append(pop_front() + " ");
		Console.Write(sb);
	}
}
