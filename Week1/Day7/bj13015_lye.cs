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
		//// 선언만 되고 사용되지 않았네요. 변수를 통해서 이름을 적절히 붙여줬으면 가독성 좋아졌을 것 같아요.:22
		int up_blank, down_blank;

		Print_firstLast(line);
		for (int i = 1; i < draw_line - 1; i++)
		{
			sb.Append(' ', i < half_draw ? i : draw_line - i - 1)
			  .Append('*').Append(' ', line - 2).Append('*')
			  .Append(' ', i < half_draw ? -2 * i + draw_line - 2 : i != half_draw ? 2 * i - draw_line : 0)
			  .Append('*', i == half_draw ? 0 : 1)
			  .Append(' ', line - 2).Append('*');
			sb.Append("\n");
		}

		Console.Write(sb);
		Print_firstLast(line);
	}

	static void Print_firstLast(int line)
	{
		StringBuilder sb = new StringBuilder();
		sb.Append('*', line)
		  .Append(' ', line * 2 - 3)
		  .Append('*', line)
		  .Append("\n");
		Console.Write(sb);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}