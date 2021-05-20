using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int bottles = Read_int();
		StringBuilder sb = new StringBuilder();
		//// 이런 String 리터럴 배열 사용은 가독성이 떨어지는 것 같습니다. 0에는 뭐가 있고 1에는 뭐가 있는지 그때그때 확인해야 돼서요
		//// : 22 index 3, 4번 처럼 한 문장씩이면 verse라는 의미에 맞을 것 같은데 1,2,3은 구절이라서 그때그때 확인해야 할 것 같아요
		string[] verse = { "of beer", "on the wall", "more bottles", "Take one down and pass it around, ", "Go to the store and buy some more, " };
		for (int i = bottles; i > 0; i--)
		{
			//// 읽기 힘듭니다. 신입으로 회사에 들어갔을 때 이처럼 쓰인 코드를 유지보수 하는 역할을 맡게되면 정말 막막할 것 같아요.
			//// 가독성면에서 아쉽기는 합니다 ㅜ
			////읽기가 좀 힘들었습니다.
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