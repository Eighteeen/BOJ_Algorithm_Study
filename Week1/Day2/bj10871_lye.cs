using System;

public class Test
{
	public static void Main()
	{
		string input;
		string[] arr = new string[2];
		int num, min;

		input = Console.ReadLine();
		arr = input.Split(' ');
		num = Convert.ToInt32(arr[0]);
		min = Convert.ToInt32(arr[1]);

		//// 변수 이름이 soo길래 순간 뇌절왔어요..
		//// '문자열 형태인 숫자의 배열'이라는 의미로 strNumArr 정도로 지으면 더 좋았을 것 같아요 : 22 soo는 조금 아쉬워요
		string[] soo = new string[num];

		input = Console.ReadLine();
		soo = input.Split(' ');

		//// 깔끔해요
		for (int i = 0; i < num; i++)
		{
			if (Convert.ToInt32(soo[i]) < min)
			{
				Console.Write(Convert.ToInt32(soo[i]));
				Console.Write(" ");
			}
		}

	}
}