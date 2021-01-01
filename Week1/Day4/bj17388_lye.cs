using System;

public class Test
{
	public static void Main()
	{
		int[] arr = Read_ints();

		if (arr[0] + arr[1] + arr[2] > 99)
		{
			Console.WriteLine("OK");
		}
		else
		{
			if (arr[0] < arr[1] && arr[0] < arr[2])
				Console.WriteLine("Soongsil");
			if (arr[1] < arr[0] && arr[1] < arr[2])
				Console.WriteLine("Korea");
			if (arr[2] < arr[0] && arr[2] < arr[1])
				Console.WriteLine("Hanyang");
		}

	}

	public static int[] Read_ints()
	{
		string input;
		int[] res = new int[3];
		input = Console.ReadLine();
		string[] tmp = input.Split(' ');

		for (int i = 0; i < 3; i++)
		{
			res[i] = Convert.ToInt32(tmp[i]);
		}
		return res;
	}
}