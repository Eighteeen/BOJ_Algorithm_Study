using System;

public class Test
{
	public static void Main()
	{
		string[] input = Console.ReadLine().Split(' ', StringSplitOptions.RemoveEmptyEntries);
		Console.Write(input.Length);
	}
}