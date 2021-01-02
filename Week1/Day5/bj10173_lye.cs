using System;

public class Test
{
	public static void Main()
	{
		string input;
		int index;

		//// 군더더기 없이 깔끔하게 짜여진거 같아요! 술술 읽혀졌습니다.
		while (true)
		{
			index = -1;
			input = Console.ReadLine();
			if (input == "EOI") return;

			index = input.IndexOf("nemo", StringComparison.OrdinalIgnoreCase);
			if (index == -1) Console.WriteLine("Missing");
			else Console.WriteLine("Found");
		}
	}
}