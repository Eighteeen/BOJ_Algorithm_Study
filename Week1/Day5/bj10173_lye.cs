using System;

public class Test
{
	public static void Main()
	{
		string input;
		int index;

		//// 군더더기 없이 깔끔하게 짜여진거 같아요! 술술 읽혀졌습니다. : 22
		while (true)
		{
			index = -1;
			input = Console.ReadLine();
			if (input == "EOI") return;

			//// 오호 IndexOf를 하면서 OrdinalIgnoreCase 를 같이 사용할 수 있군요! C# 메소드 활용이 너무 좋네요
			index = input.IndexOf("nemo", StringComparison.OrdinalIgnoreCase);
			if (index == -1) Console.WriteLine("Missing");
			else Console.WriteLine("Found");
		}
	}
}