using System;
//���� �������� �ʰ� �� ������ ����ȭ�ǰ� ®���ϴ�.
public class Test
{
	public static void Main()
	{
		string answer = "1234512345";
		int studentsCnt = Convert.ToInt32(Console.ReadLine());
		int cnt;
		for (int i = 0; i < studentsCnt; i++)
		{
			//// 답에 띄어쓰기를 포함했다면 Replace해주지 않아도 됐을 것 같아요!
			string input = Console.ReadLine().Replace(" ", "");
			cnt = 0;

			for (int j = 0; j < input.Length; j++)
				if (input[j] == answer[j]) cnt++;

			if (cnt == 10) Console.WriteLine(i + 1);
		}
	}
}