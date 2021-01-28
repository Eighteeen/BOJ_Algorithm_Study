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
			//// 탭을 4번이나 할 정도로 코드가 깊습니다. 조금 더 정돈되면 좋을 것 같아요 
				//// 예를 들자면 if (isBreak) continue;를 하고 그 아래에 43~53줄을 적었다면 약간 더 간단해질 수 있었습니다
				//// 그 외에도 클래스, 함수 등으로 정돈하면 더 좋을 것 같아용
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
	//// InsertValue라는 이름으로는 '값을 삽입하는 함수' 정도의 정보만 줄 수 있습니다
	//// stringArrayToDeque 등으로 이름을 지어 정보를 더 주었다면 더 직관적이었을 것 같아요!
	static void InsertValue(Dequeue deque, int n)
	{
		string[] strArr = Console.ReadLine().Replace("[", "").Replace("]", "").Split(',');
		for (int i = 0; i < n; i++)
		{
			deque.push_back(Convert.ToInt32(strArr[i]));
		}
	}
}

//// 직접 만든 클래스로 작업하는거 멋쪄요
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
