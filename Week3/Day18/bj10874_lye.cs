using System;
//재사용 고려하지 않고 이 문제에 최적화되게 짰습니다.
public class Test
{
	public static void Main()
	{
		string answer = "1234512345";
		int studentsCnt = Convert.ToInt32(Console.ReadLine());
		int cnt;
		for (int i = 0; i < studentsCnt; i++)
		{
			string input = Console.ReadLine().Replace(" ", "");
			cnt = 0;

			for (int j = 0; j < input.Length; j++)
				if (input[j] == answer[j]) cnt++;

			if (cnt == 10) Console.WriteLine(i + 1);
		}
	}
}