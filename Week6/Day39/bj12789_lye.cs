using System;
using System.Collections;

//// 클래스 활용해보려고 연습하시는거 굳굳
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
				//// FindStudent는 어때요? 같은 것을 한쪽에서는 student로 칭하고, 한쪽에서는 num으로 칭하고 있는 거 같아요
				//// 그리고 이왕 함수로 추상화하는거 큐가 비어있는지 확인하는 것도 FindNumInQueue가 확인하게했으면 더 깔끔했을 것 같아요!
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