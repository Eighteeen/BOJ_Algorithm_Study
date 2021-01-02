using System;

public class Test
{
	public static void Main()
	{
		////깔끔한거 같습니다. : 22
		int[] numArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		int[] section = new int[2];

		for (int i = 0; i < 10; i++)
		{
			//// 이전에도 피드백했지만 이렇게 Read_ints()로 쓰니가 정말 코드가 훨씬 깔끔해보여요!
			section = Read_ints();
			//// 입력받아온 값을 새로운 값으로 덮어씌우는게 조금 의아했어요
			//// 새로이 변수를 선언하여 이름을 지어주는 것도 가독성면에서 나쁘지 않았을 것 같습니다 : 22
			section[1] = section[1] - section[0] + 1;
			//// 와우 Array.Reverse는 처음 들어봐요. C# 기능 적절히 잘 활용해주신 것 같습니다
			Array.Reverse(numArr, section[0] - 1, section[1]);
		}

		for (int i = 0; i < 20; i++)
		{
			Console.Write(numArr[i] + " ");
		}
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int[] result = { Convert.ToInt32(strArr[0]), Convert.ToInt32(strArr[1]) };
		return result;
	}
}