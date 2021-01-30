using System;
using System.Collections;
using System.Text;
using System.Linq;
using System.Collections.Generic;
public class Test
{
	public static void Main()
	{
		int testCnt = Convert.ToInt32(Console.ReadLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < testCnt; i++)
		{
			int[] caseValue = ReadInt();
			int numCnt = caseValue[0];
			int findIndex = caseValue[1];
			PrinterQueue pq = new PrinterQueue();

			pq.FillQueue(ReadInt());
			sb.Append(pq.FindPrintNum(numCnt, findIndex) + "\n");
		}
		Console.Write(sb);
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
public class PrinterQueue
{
	Queue printerQ = new Queue();
	Queue indexQ = new Queue();

	public void FillQueue(int[] numArr)
	{
		for (int i = 0; i < numArr.Length; i++)
		{
			printerQ.Enqueue(numArr[i]);
			indexQ.Enqueue(i);
		}
	}
	public bool IsMax(int now)
	{
		int max = 0;
		for (int i = 0; i < printerQ.Count; i++)
		{
			int nowPop = Convert.ToInt32(printerQ.Dequeue());
			if (nowPop > max) max = nowPop;
			printerQ.Enqueue(nowPop);
		}
		return max <= now;
	}
	public int FindPrintNum(int numCnt, int find)
	{
		while (printerQ.Count != 0)
		{
			int nowPop = Convert.ToInt32(printerQ.Dequeue());
			int nowIndex = Convert.ToInt32(indexQ.Dequeue());

			if (IsMax(nowPop))
			{
				if (nowIndex == find)
					return numCnt - printerQ.Count;
				continue;
			}
			printerQ.Enqueue(nowPop);
			indexQ.Enqueue(nowIndex);
		}
		return 1;
	}
}