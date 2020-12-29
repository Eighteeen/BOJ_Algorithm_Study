using System;

public class Test
{
	public static void Main()
	{
		string input;
		int num;
		int jul;
		input = Console.ReadLine();
		num = Convert.ToInt32(input);
		jul = 2 * num - 1;

		for (int i = 0; i < jul; i++)
		{
			//// 하나의 for문으로 조건식만 다르게 하면 if문을 쓰지 않아도 똑같은 동작을 하게 할 수 있을 것 같습니다. : 22
			if (i < num)
			{
				for (int j = 0; j < i + 1; j++)
				{
					Console.Write("*");
				}
			}
			else
			{
				//// n개의 *을 출력하는 반복문인건 바로 위의 for문과 동일한데 대입문과 조건식은 서로 다르네요? 다르게 할 이유가 있을까요?
				for (int j = i; j < jul; j++)
				{
					Console.Write("*");
				}
			}
			//// 꿀팁: Console.WriteLine(); 으로 써도 똑같습니다
			if (i != jul - 1) Console.Write("\n");
		}
	}
}