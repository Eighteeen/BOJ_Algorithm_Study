using System;
using System.Linq;
using System.Text;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int[] input = Read_ints();
		int numCnt = input[0];
		int removeIndex = input[1];
		string res = "";
		Queue queue = new Queue();
		int cnt = 0, nowPop;

		for (int i = 0; i < numCnt; i++)
		{
			queue.Enqueue(i + 1);
		}
		while (queue.Count != 0)
		{
			cnt++;
			nowPop = Convert.ToInt32(queue.Dequeue());
			if (cnt == removeIndex)
			{
				res += nowPop;
				if (queue.Count != 0) res += ", ";
				cnt = 0;
			}
			else
				queue.Enqueue(nowPop);
		}

		Console.Write("<" + res + ">");
	}
	static int[] Read_ints()
	{
		string[] strArr = Console.ReadLine().Split(' ');
		int len = strArr.Length;
		int[] numArr = new int[len];
		numArr[0] = Convert.ToInt32(strArr[0]);
		numArr[1] = Convert.ToInt32(strArr[1]);
		return numArr;
	}
}