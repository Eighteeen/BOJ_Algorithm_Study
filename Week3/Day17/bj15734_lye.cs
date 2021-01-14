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
		//// less와 more만으로는 변수의 역할을 바로 이해하기 어려웠어요. 적은 멤버? 많은 멤버?
		//// minorFootMems/majorFootMems 등등으로 변수명을 지었다면 더 명확했을 것 같습니다
		int lessMems = leftFootMems > rightFootMems ? rightFootMems : leftFootMems;
		int moreMems = allMems - lessMems - bothFootMems;

		//// 재밌는 로직이네요!
		while (bothFootMems != 0)
		{
			if (lessMems == moreMems)
			{
				//// 2로 나눠놓고 다시 2를 곱하면 같지 않나요?
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