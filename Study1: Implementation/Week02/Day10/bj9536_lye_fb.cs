using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int repeat = Read_int();
		StringBuilder sb = new StringBuilder();

		for (int k = 0; k < repeat; k++)
		{
			List<string> record = Read_record();
			List<string> anim = Animal_sound();

			for (int i = 0; i < anim.Count; i++)
			{
				record.RemoveAll(d => d.Equals(anim[i]));
			}
			for (int i = 0; i < record.Count; i++)
			{
				sb.Append(record[i] + " ");
			}
			sb.Append("\n");
		}
		Console.Write(sb);

	}
	static List<string> Read_record()
	{
		string input = "";
		input = Console.ReadLine();
		List<string> rec = input.Split(' ').ToList();
		return rec;
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static List<string> Animal_sound()
	{
		string input = Console.ReadLine();
		int sound;
		List<string> animal = new List<string>();

		while (input != "what does the fox say?")
		{
			string[] strArr = input.Split(' ');
			sound = Array.IndexOf(strArr, "goes") + 1;
			animal.Add(strArr[sound]);
			input = Console.ReadLine();
		}
		return animal;
	}
}