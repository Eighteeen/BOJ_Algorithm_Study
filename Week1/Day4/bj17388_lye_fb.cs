using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int[] arr = Read_ints(3);

		if ( arr.Sum() > 99)
		{
			Console.WriteLine("OK");
		}
		else
		{
			if (arr.Min()==arr[0])
				Console.WriteLine("Soongsil");
			if (arr.Min() == arr[1])
				Console.WriteLine("Korea");
			if (arr.Min() == arr[2])
				Console.WriteLine("Hanyang");
		}

	}

	public static int[] Read_ints(int n)
	{
		string input;
		int[] res = new int[n];
		input = Console.ReadLine();
		string[] tmp = input.Split(' ');

		for (int i = 0; i < n; i++)
		{
			res[i] = Convert.ToInt32(tmp[i]);
		}
		return res;
	}
}