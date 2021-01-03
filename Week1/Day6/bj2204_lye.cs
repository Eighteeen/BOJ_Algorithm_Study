using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Read_int();
		int compare;
		string value;
		//// "ZZZZZ"같은게 입력으로 들어오면 어쩌려고 "ZZZ"를 min 기본값으로 두셨지? 이게 정답 처리되긴 한건가? 싶었는데
		//// 보니까 아래 for문을 거치면서 "ZZZ"과 전혀 무관하게, 첫 줄을 입력 받아 min으로 넣고 있었네요
		//// 오독하게 만드므로 "" 또는 null로 초기화하는게 좋을 것 같습니다.
		string min = "ZZZ";
		StringBuilder sb = new StringBuilder();

		while (line != 0)
		{
			//// while문의 맨 앞에서 첫줄을 min으로 입력받고 for문의 i를 1부터 시작했으면 훨씬 깔끔했을 것 같아요
			//// 왜 삼항 연산자를 저렇게 중첩 2번씩, 총 2개를 쓰신건가 싶었습니다.
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