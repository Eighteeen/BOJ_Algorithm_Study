using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.Length; i++)
		{
			sb.Append(input[i].ToString());
			if (i % 10 == 9)
			{
				sb.Append("\n");
			}

		}

		Console.Write(sb);
	}
}