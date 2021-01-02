using System;

//// 깔끔하게 짜려는 고민이 보이는 것 같습니다! 잘 읽혔어요
public class Test
{
	public static void Main()
	{	////제가 c를 잘 몰라서 그런데 int line = Read_ints(false)[0];이 부분은 무엇을 의미하는 건가요.
		int line = Read_ints(false)[0];
		int[] input = new int[2];
		int[,] bulk = new int[line, 2];
		int cnt;

		for (int i = 0; i < line; i++)
		{
			input = Read_ints(true);
			//// ㅋㅋㅋㅋㅋ bulk라는 작명 직관적이면서 재밌는거같아요
			bulk[i, 0] = input[0];
			bulk[i, 1] = input[1];
		}

		for (int i = 0; i < line; i++)
		{
			cnt = 0;
			for (int j = 0; j < line; j++)
			{
				if (bulk[j, 0] > bulk[i, 0] && bulk[j, 1] > bulk[i, 1])
					cnt++;
			}
			Console.Write(cnt + 1 + " ");
		}
	}
	//// 한 정수만 입력받는 함수, 두 정수를 입력받는 함수. 이렇게 두 개의 함수로 나누는 게 더 깔끔했을 것 같아요!
	//// 한 함수로 묶기에는 중복되는 코드가 그렇게 많지않고, 한 정수만 불러올 경우 뒤에 [0]을 붙이는게 저로선 좀 부자연스러워 보였어요.
	static int[] Read_ints(bool isArr)
	{
		string input = Console.ReadLine();
		if (isArr == false)
		{
			int[] intArr = { Convert.ToInt32(input) };
			return intArr;
		}
		else
		{
			string[] strArr = input.Split(' ');
			int[] intArr = { Convert.ToInt32(strArr[0]), Convert.ToInt32(strArr[1]) };
			return intArr;
		}

	}
}