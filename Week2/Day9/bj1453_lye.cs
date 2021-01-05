using System;

public class Test
{
	public static void Main()
	{
		int customers = Read_int();
		string[] selectNums = get_Numbers();
		int cnt = 0, isNo;
		for(int i=0; i<selectNums.Length; i++)
		{
			isNo = Array.IndexOf(selectNums, selectNums[i], 0 , i);
			if(isNo != -1) cnt++;
		}
		
		Console.Write(cnt);
	}
	
	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
	
	static string[] get_Numbers()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		return strArr;
	}
}