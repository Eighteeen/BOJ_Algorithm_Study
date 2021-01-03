using System;

public class Test
{
	////정말 깔끔하네요.
	public static void Main()
	{
		//// C#의 기능을 적절히 잘 찾아 활용하시는 모습이 멋집니다.
		string[] input = Console.ReadLine().Split(' ', StringSplitOptions.RemoveEmptyEntries);
		Console.Write(input.Length);
	}
}