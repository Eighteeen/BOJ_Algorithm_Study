using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Read_int();
		int draw_line = 2 * line - 1;
		int half_draw = draw_line / 2;
		StringBuilder sb = new StringBuilder();
		int up_blank, down_blank;

		sb.Append(Print_firstLast(line));
		for (int i = 1; i < draw_line - 1; i++)
		{
			up_blank = -2 * i + draw_line - 2;
			down_blank = 2 * i - draw_line;
			sb.Append(' ', i < half_draw ? i : draw_line - i - 1)
			  .Append('*').Append(' ', line - 2).Append('*')
			  .Append(' ', i < half_draw ? up_blank : (i != half_draw ? down_blank : 0));
			  .Append('*', i == half_draw ? 0 : 1)
			  .Append(' ', line - 2).Append('*');
			sb.Append("\n");
		}

		sb.Append(Print_firstLast(line));
		Console.Write(sb);
	}

	static StringBuilder Print_firstLast(int line)
	{
		StringBuilder sb = new StringBuilder();
		sb.Append('*', line)
		  .Append(' ', line * 2 - 3)
		  .Append('*', line)
		  .Append("\n");
		return sb;
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}