using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int[] members = Read_ints();
		int leftFootMems = members[0];
		int rightFootMems = members[1];
		int bothFootMems = members[2];
		int allMems = members.Sum();
		int lessCntMems = leftFootMems > rightFootMems ? rightFootMems : leftFootMems;
		int moreCntMems = allMems - lessCntMems - bothFootMems;


		while (bothFootMems != 0)
		{
			if (lessCntMems == moreCntMems)
			{
				Console.Write(lessCntMems * 2 + bothFootMems / 2 * 2);
				return;
			}
			lessCntMems++;
			bothFootMems--;
			moreCntMems = allMems - lessCntMems - bothFootMems;
		}

		Console.Write(lessCntMems * 2);

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