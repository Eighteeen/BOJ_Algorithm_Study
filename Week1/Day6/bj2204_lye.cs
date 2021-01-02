using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Read_int();
		int compare;
		string value;
		string min = "ZZZ";
		StringBuilder sb = new StringBuilder();

		while (line != 0)
		{
			for (int i = 0; i < line; i++)
			{
				value = Console.ReadLine();
				compare = (i == 0 ? 0 : string.Compare(min, value, true));
				min = (compare == 0 ? value : (compare < 0 ? min : value));
			}

			sb.Append(min);
			sb.Append("\n");
			line = Read_int();
		}
		Console.Write(sb);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}