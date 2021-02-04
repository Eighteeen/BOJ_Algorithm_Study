using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int cnt = Convert.ToInt32(Console.ReadLine());
		SnackLineUp sl = new SnackLineUp(ReadInt(cnt), cnt);

		sl.LineCheck();
		if (sl.IsNice())
		{
			Console.Write("Nice");
			return;
		}
		Console.Write("Sad");
	}
	static int[] ReadInt(int cnt)
	{
		string[] input = Console.ReadLine().Split(' ');
		int[] numArr = new int[cnt];
		for (int i = 0; i < cnt; i++)
		{
			numArr[i] = Convert.ToInt32(input[i]);
		}
		return numArr;
	}
}
public class SnackLineUp
{
	Stack st = new Stack();
	Queue que;
	int studentCnt;

	public SnackLineUp(int[] tickets, int cnt)
	{
		que = new Queue(tickets);
		studentCnt = cnt;
	}
	public bool FindNumInQue(int n)
	{
		return Convert.ToInt32(que.Peek()) == n;
	}
	public bool FindNumInSt(int n)
	{
		return Convert.ToInt32(st.Peek()) == n;
	}
	public void LineCheck()
	{
		for (int i = 1; i <= studentCnt; i++)
		{
			while (true)
			{
				if (que.Count != 0 && FindNumInQue(i))
				{
					que.Dequeue();
					break;
				}
				if (st.Count != 0 && FindNumInSt(i))
				{
					st.Pop();
					break;
				}
				if (que.Count != 0) st.Push(que.Dequeue());
				else break;
			}
		}
	}
	public bool IsNice()
	{
		return que.Count == 0 && st.Count == 0;
	}


}