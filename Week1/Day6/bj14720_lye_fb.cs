using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int toEatMilk = 0,cnt=0;
		int cntStore = Read_int();
		int[] stores = Read_ints();

		for (int i = 0; i < stores.Length; i++)
		{
			if (stores[i] == toEatMilk)
            {
				cnt++;
				toEatMilk = (toEatMilk + 1) % 3;
			}
				
		}

		Console.Write(cnt);

	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static int[] Read_ints() 
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int len = strArr.Length;
		int[] intArr = new int[len];
		for (int i = 0; i < len; i++)
		{
			intArr[i] = Convert.ToInt32(strArr[i]);
		}

		return intArr;

	}


}