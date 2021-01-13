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
		int lessMems = leftFootMems > rightFootMems ? rightFootMems : leftFootMems;
		int moreMems = allMems - lessMems - bothFootMems;


		while (bothFootMems != 0)
		{
			if (lessMems == moreMems)
			{
				Console.Write(lessMems * 2 + bothFootMems / 2 * 2);
				return;
			}
			lessMems++;
			bothFootMems--;
			moreMems = allMems - lessMems - bothFootMems;
		}

		Console.Write(lessMems * 2);

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