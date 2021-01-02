using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		double[] numbers = Read_ints();
		double sum = numbers.Sum();
		Console.Write(sum);
	}

	static double[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		string first = strArr[0] + strArr[1];
		string second = strArr[2] + strArr[3];
		double[] numArr = { Convert.ToDouble(first), Convert.ToDouble(second) };
		return numArr;
	}
}