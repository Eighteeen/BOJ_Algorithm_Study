using System;

public class Test
{
	//// 깔꼬미 : 22
	public static void Main()
	{
		int howManyFriends = Read_int();
		int howManyGames = Read_int();
		int[] targets = Read_ints();
		int[] friendsScores = new int[howManyFriends];

		for (int i = 0; i < howManyGames; i++)
		{
			int[] guessPapers = Read_ints();
			for (int j = 0; j < howManyFriends; j++)
			{
				if (targets[i] == guessPapers[j])
					friendsScores[j]++;
				else
					friendsScores[targets[i] - 1]++;
			}
		}

		for (int i = 0; i < howManyFriends; i++)
		{
			Console.WriteLine(friendsScores[i]);
		}

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
		int[] nums = new int[len];
		for (int i = 0; i < len; i++)
			nums[i] = Convert.ToInt32(strArr[i]);
		return nums;
	}
}