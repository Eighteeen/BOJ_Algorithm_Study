using System;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int persons = Read_int();
		int[] howManyName = new int[26];
		char nowName;
		int isFind;
		string result = "";

		for (int i = 0; i < persons; i++)
		{
			nowName = Read_NameOfFirst();
			howManyName[nowName - 'a'] += 1;
		}
		for (int i = 0; i < howManyName.Length; i++)
		{
			if (howManyName[i] > 4)
				result += Convert.ToChar(i + 97);
		}
		if (result == "") Console.Write("PREDAJA");
		else Console.Write(result);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
	static char Read_NameOfFirst()
	{
		string input = Console.ReadLine();
		return input[0];
	}

}