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
			Console.Write(input[i].ToString());
			if (i % 10 == 9) { Console.Write("\n"); }

		}
	}
}