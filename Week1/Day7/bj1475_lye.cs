using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int[] numbers = new int[10];
		int[] roomNum = Read_ints();
		int now, sixNineValue, result, sixAndNine;
		for (int i = 0; i < roomNum.Length; i++)
		{
			now = roomNum[i];
			numbers[now]++;
		}
		sixAndNine = numbers[6] + numbers[9];
		sixNineValue = sixAndNine % 2 == 0 ? sixAndNine / 2 : sixAndNine / 2 + 1;
		numbers[6] = numbers[9] = 0;
		result = sixNineValue > numbers.Max() ? sixNineValue : numbers.Max();
		Console.Write(result);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		int[] numArr = new int[input.Length];
		for (int i = 0; i < input.Length; i++)
		{
			numArr[i] = Int32.Parse(input[i].ToString());
		}
		return numArr;
	}
}