using System;

public class Test
{
	public static void Main()
	{
		string input;
		input = Console.ReadLine();
		//// 변수명을 한 글자로 지으니까 반복문에 사용되는 변수 같아요. len, length처럼 지어주면 더 가독성 좋을 것 같습니다.
		int l = input.Length;
		int cnt = 0;

		for (int i = 0; i < l / 2; i++)
		{
			//// 한번이라도 서로 다를 때 바로 0을 출력하게 했다면 더 효율적이었을 것 같습니다. : 22
			if (input[i].ToString() == input[l - i - 1].ToString())
			{
				cnt++;
			}
		}

		if (cnt == l / 2) { Console.WriteLine("1"); }
		else { Console.WriteLine("0"); }
	}
}