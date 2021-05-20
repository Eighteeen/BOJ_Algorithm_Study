using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int battleNum = Read_int();
		int dalf, uron;
		StringBuilder sb = new StringBuilder();
		string[] result = { "Evil eradicates all trace of Good", "Good triumphs over Evil", "No victor on this battle field" };
		string dalf_win = result[1];
		string uron_win = result[0];
		string no_victor = result[2];
		for (int i = 0; i < battleNum; i++)
		{
			dalf = Sum_Gandalf(Read_ints());
			uron = Sum_Sauron(Read_ints());
			sb.Append("Battle ").Append(i + 1).Append(": ");
			if (dalf == uron)
				sb.Append(no_victor);
			else
				sb.Append(dalf > uron ? dalf_win : uron_win);
			sb.Append('\n');
		}

		Console.Write(sb);
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
		int[] numArr = new int[strArr.Length];
		for (int i = 0; i < strArr.Length; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}

	static int Sum_Gandalf(int[] num)
	{
		int[] dalfArmy = { 1, 2, 3, 3, 4, 10 };
		int warriors = dalfArmy.Length;
		int sum = 0;
		for (int i = 0; i < warriors; i++)
			sum += dalfArmy[i] * num[i];
		return sum;
	}

	static int Sum_Sauron(int[] num)
	{
		int[] uronArmy = { 1, 2, 2, 2, 3, 5, 10 };
		int warriors = uronArmy.Length;
		int sum = 0;
		for (int i = 0; i < warriors; i++)
			sum += uronArmy[i] * num[i];
		return sum;
	}
}