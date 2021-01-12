using System;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int persons = Read_int();
		List<char> playerList = new List<char>();
		int[] howManyName = new int[persons];
		char nowName;
		int isFind;
		string result = "";

		for (int i = 0; i < persons; i++)
		{
			nowName = Read_lastName();
			isFind = playerList.IndexOf(nowName);
			if (isFind == -1)
			{
				playerList.Add(nowName);
				isFind = playerList.IndexOf(nowName);
			}
			howManyName[isFind] += 1;
		}
		for (int i = 0; i < howManyName.Length; i++)
		{
			if (howManyName[i] > 4)
				result += playerList[i];
		}
		if (result == "") Console.Write("PREDAJA");
		else Console.Write(Sort_result(result));
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
	static char Read_lastName()
	{
		string input = Console.ReadLine();
		return input[0];
	}
	static string Sort_result(string res)
	{
		int len = res.Length;
		char[] tmp = new char[len];
		for (int i = 0; i < len; i++)
			tmp[i] = res[i];
		Array.Sort(tmp);
		res = "";
		for (int i = 0; i < len; i++)
			res += tmp[i];
		return res;
	}
}