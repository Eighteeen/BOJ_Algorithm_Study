using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int bottles = Read_int();
		StringBuilder sb = new StringBuilder();
		string[] verse = { "of beer", "on the wall", "more bottles", "Take one down and pass it around, ", "Go to the store and buy some more, " };
		for (int i = bottles; i > 0; i--)
		{
			sb.Append(i + " " + (i == 1 ? "bottle " : "bottles ") + verse[0] + ' ' + verse[1] + ", ");
			sb.Append(i + (i == 1 ? " bottle " : " bottles ") + verse[0] + ".\n");
			sb.Append(verse[3] + (i > 1 ? (i - 1).ToString() : "no") + " " + (i > 2 ? "bottles " : i == 2 ? "bottle " : verse[2] + " "));
			sb.Append(verse[0] + " " + verse[1] + ".");
			sb.Append('\n', 2);
		}
		sb.Append("No " + verse[2] + " " + verse[0] + " " + verse[1] + ", ");
		sb.Append("no " + verse[2] + " " + verse[0] + ".\n");
		sb.Append(verse[4] + bottles + (bottles == 1 ? " bottle " : " bottles ") + verse[0] + " " + verse[1] + ".");

		Console.Write(sb);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}