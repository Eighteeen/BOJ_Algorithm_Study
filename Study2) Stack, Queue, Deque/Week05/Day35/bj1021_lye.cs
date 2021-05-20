using System;
using System.Collections.Generic;

//// 전체적으로 깔끔하고 효율적인 것 같습니다
public class Test
{
	//// 와우 클래스 활용으로 main이 정말 깔끔하네요
	public static void Main()
	{
		int[] condition = ReadInt();
		int dequeSize = condition[0];
		int findCnt = condition[1];
		int[] findNum = ReadInt();
		Dequeue deque = new Dequeue();
		deque.FillDequeue(dequeSize);
		int cnt = 0;

		for (int i = 0; i < findCnt; i++)
		{
			int nowNum = findNum[i];
			string toInvokeMethod = deque.ChooseFrontOrBack(nowNum);

			while (nowNum != deque.front())
			{
				//// 활용성이 좋네요 :22 좋은 시도인거같습니다 깔끔한 코드를 위해 신경쓰신게 눈에 띄네요!
				deque.GetType().GetMethod(toInvokeMethod).Invoke(deque, null);
				cnt++;
			}
			deque.pop_front();
		}
		Console.Write(cnt);
	}
	static int[] ReadInt()
	{
		string[] charArr = Console.ReadLine().Split();
		int len = charArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(charArr[i]);
		return numArr;
	}
}
public class Dequeue
{
	List<int> dequeue = new List<int>();
	int frontIndex = 0;

	public void push_front(int input)
	{
		dequeue.Insert(frontIndex, input);
	}
	public void push_back(int input)
	{
		dequeue.Add(input);
	}
	public int pop_front()
	{
		int res = dequeue[frontIndex];
		dequeue.RemoveAt(frontIndex);
		return res;
	}
	public int pop_back()
	{
		int index = dequeue.Count - 1;
		int res = dequeue[index];
		dequeue.RemoveAt(index);
		return res;

	}
	public int front()
	{
		return dequeue[frontIndex];
	}
	public int back()
	{
		int index = dequeue.Count - 1;
		return dequeue[index];
	}
	public void FillDequeue(int n)
	{
		for (int i = 1; i <= n; i++)
		{
			push_back(i);
		}
	}
	public int FindIndex(int num)
	{
		return dequeue.IndexOf(num);
	}
	//// 효율적인 것 같습니다
	public string ChooseFrontOrBack(int num)
	{
		int fgap = Math.Abs(FindIndex(num) - frontIndex);
		int bgap = Math.Abs(FindIndex(num) - FindIndex(back()));
		if (fgap > bgap)
			return "BackToFront";
		else
			return "FrontToBack";
	}
	public void FrontToBack()
	{
		int nowPop = Convert.ToInt32(pop_front());
		push_back(nowPop);
	}
	public void BackToFront()
	{
		int nowPop = Convert.ToInt32(pop_back());
		push_front(nowPop);
	}
}
