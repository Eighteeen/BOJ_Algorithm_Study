using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		while (true)
		{
			int[] arr = ts.Read_arr();

			if (arr[0] == arr[1] && arr[0] == 0) break;
			ts.Compare(arr[0], arr[1]);
		}
	}

	public int[] Read_arr()
	{
		string input;
		string[] arr_s = new string[2];
		int[] arr_i = new int[2];
		input = Console.ReadLine();
		arr_s = input.Split(' ');
		arr_i[0] = Convert.ToInt32(arr_s[0]);
		arr_i[1] = Convert.ToInt32(arr_s[1]);
		return arr_i;
	}

	public void Compare(int aa, int bb)
	{
		if (aa % bb == 0 || bb % aa == 0)
		{
			if (aa > bb) { Console.WriteLine("multiple"); }
			else { Console.WriteLine("factor"); }
		}
		else
		{
			Console.WriteLine("neither");
		}


	}
}