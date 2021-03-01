using System;
using System.IO;

public class Test
{

//// 허탈할 정도로 짧고 깔끔합니다	
	public static void Main()
	{
		int numOfDecimal = Read_int();
		string strBinaryNum = Convert.ToString(numOfDecimal, 2);
		Console.Write(strBinaryNum.Length);
	}
	
	static int Read_int()
	{
		StreamReader reader = new StreamReader(Console.OpenStandardInput());
		return int.Parse(reader.ReadLine());
	}
}