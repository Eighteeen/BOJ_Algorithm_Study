using System;
using System.Text;
using System.Collections;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int[] info = ReadInt();
		int customerCnt = info[2];
		StaffTask st = new StaffTask(info[0], info[1]);
		
		for(int i=0; i<customerCnt; i++)
		{
			string[] orderInfo = Console.ReadLine().Split(' ');
			string color = orderInfo[1];
			int second = Convert.ToInt32(orderInfo[0]);
			int cnt = Convert.ToInt32(orderInfo[2]);
			
			st.RecordWrappingTime(color, second, cnt);
		}
		st.GrantWrappingNum();
	}
	static int[] ReadInt()
	{
		string[] input = Console.ReadLine().Split(' ');
		int len = input.Length;
		int[] numArr = new int[len];
		for(int i=0; i<len; i++)
		{
			numArr[i] = Convert.ToInt32(input[i]);
		}
		return numArr;
	}
}
public class StaffTask
{
	Queue sangmin = new Queue();
	Queue jisu = new Queue();
	int smSpeed, jsSpeed;
	int smCnt=0, jsCnt=0;
	int smLast, jsLast;
	
	public StaffTask(int a, int b)
	{
		smSpeed = a;
		jsSpeed = b;
	}
	public int ToInt(object obj)
	{
		return Convert.ToInt32(obj);
	}
	public void RecordWrappingTime(string color, int time, int cnt)
	{
		int startTime = time;
		
		if(color == "R")
			{
				if(jisu.Count != 0)
				{
					startTime = jsLast > time ? jsLast : time;
				}
				for(int i=0; i<cnt; i++)
				{
					jisu.Enqueue(startTime);
					startTime += jsSpeed;
					jsLast = startTime;
				}
				jsCnt += cnt;
			}
		if(color == "B")
			{
				if(sangmin.Count != 0)
				{
					startTime = smLast > time ? smLast : time;	
				}
				for(int i=0; i<cnt; i++)
				{
					sangmin.Enqueue(startTime);
					startTime += smSpeed;
					smLast = startTime;
				}
				smCnt += cnt;
			}
	}
	
	public void GrantWrappingNum()
	{
		List<int> smGiftNum = new List<int>();
		List<int> jsGiftNum = new List<int>();
		int smSecond = (sangmin.Count == 0) ? 86401 : ToInt(sangmin.Dequeue());
		int jsSecond = (jisu.Count == 0) ? 86401 : ToInt(jisu.Dequeue());
		
		for(int i=1; i<= smCnt+jsCnt; i++)
		{
			if(smSecond <= jsSecond)
			{
				smGiftNum.Add(i);
				smSecond = (sangmin.Count == 0) ? 86401 : ToInt(sangmin.Dequeue());
				continue;
			}
			jsGiftNum.Add(i);
			jsSecond = (jisu.Count == 0) ? 86401 : ToInt(jisu.Dequeue());
		}
		PrintWrappingList(smGiftNum, jsGiftNum);
	}
	public void PrintWrappingList(List<int> sm, List<int> js)
	{
		StringBuilder sb = new StringBuilder();
		sb.Append(smCnt).Append("\n");
		for(int i=0; i<sm.Count; i++)
		{
			sb.Append(sm[i]).Append(" ");
		}
		sb.Append("\n");
		sb.Append(jsCnt).Append("\n");
		for(int i=0; i<js.Count; i++)
		{
			sb.Append(js[i]).Append(" ");
		}
		Console.Write(sb);
	}
	
}