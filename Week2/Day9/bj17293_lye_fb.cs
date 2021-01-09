using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int bottles = Read_int();
		StringBuilder sb = new StringBuilder();
		for (int i = bottles; i > 0; i--)
		{
			if (i > 1) sb.Append(TwoMoreBottles(i));
			else sb.Append(OneBottle());
			sb.Append("\n");
		}
		sb.Append(zeroBottles(bottles));
		Console.Write(sb);
	}
	static StringBuilder TwoMoreBottles(int n)
	{
		string isBottle = ((n == 2) ? " bottle " : " bottles ");
		StringBuilder twosb = new StringBuilder();
		twosb.Append(n + " bottles of beer on the wall, " + n + " bottles of beer.\n");
		twosb.Append("Take one down and pass it around, " + (n-1) + isBottle + "of beer on the wall.\n");
		return twosb;
	}
	static StringBuilder OneBottle()
	{
		StringBuilder onesb = new StringBuilder();
		onesb.Append("1 bottle of beer on the wall, 1 bottle of beer.\n");
		onesb.Append("Take one down and pass it around, no more bottles of beer on the wall.\n");
		return onesb;
	}
	static StringBuilder zeroBottles(int n)
	{
		string isBottle = ((n == 1) ? " bottle " : " bottles ");
		StringBuilder zerosb = new StringBuilder();
		zerosb.Append("No more bottles of beer on the wall, no more bottles of beer.\n");
		zerosb.Append("Go to the store and buy some more, " + n + isBottle + "of beer on the wall.\n");
		return zerosb;
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
}