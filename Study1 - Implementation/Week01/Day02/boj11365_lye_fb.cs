using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		string input;
		int len;
		StringBuilder sb = new StringBuilder();

		while (true)
		{
			input = Console.ReadLine();
			len = input.Length;
			if (input == "END") break;
			for (int i = len; i > 0; i--)
			{
				sb.Append(input[i - 1].ToString());
			}
			sb.Append('\n');
		}

		Console.Write(sb);
	}
}