using System;
using System.Text;
using System.IO;

public class Test
{
	public static void Main()
	{
		StreamReader reader = new StreamReader(Console.OpenStandardInput());
		StreamWriter writer = new StreamWriter(Console.OpenStandardOutput());
		int line = int.Parse(reader.ReadLine());
		int[] numArr = new int[10001];
		int num;

		for (int i = 0; i < line; i++)
		{
			num = int.Parse(reader.ReadLine());
			numArr[num] = numArr[num] + 1;
		}

		for (int i = 0; i < numArr.Length; i++)
		{
			for (int j = 0; j < numArr[i]; j++)
			{
				writer.WriteLine(i);
			}
		}

		writer.Close();
		reader.Close();


	}

}