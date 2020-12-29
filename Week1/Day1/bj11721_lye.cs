using System;

//// 흠 잡을 데 없이 깔끔한 것 같습니다.
public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();

		for (int i = 0; i < input.Length; i++)
		{
			//// 하나씩 print하게 되면 메모리 낭비가 될 것 같아요. C#도 열글자 한번에 쓸 수 있는 방법이 있을 것 같아요!
			Console.Write(input[i].ToString());
			if (i % 10 == 9) { Console.Write("\n"); }

		}
	}
}