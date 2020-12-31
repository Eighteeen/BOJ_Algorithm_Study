using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		int jul, loc;
		jul = ts.Read_int();
		//// 한국어 발음식 변수는 정말 필요한 경우에만 쓰는 게 좋을 것 같아요. 한번에 무엇인지 알아보기 어렵네요 ㅠ :22 동의합니다

		for (int i = 0; i < jul; i++)
		{
			string words = Console.ReadLine();
			string[] arr = words.Split(' ');
			//// loc는 어떤걸 줄인 말인가요?? (순수하게 궁금한 것)
			loc = Convert.ToInt32(arr[0]);
			words = arr[1];

			for (int j = 0; j < words.Length; j++)
			{
				if (j != loc - 1)
				{
					Console.Write(words[j].ToString());
				}
			}

			Console.WriteLine();
		}

	}

	public int Read_int()
	{
		string input;
		input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}