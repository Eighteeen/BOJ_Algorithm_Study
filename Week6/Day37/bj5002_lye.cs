using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int maxRemember = Convert.ToInt32(Console.ReadLine());
		char[] customers = getCustomerArr();
		int customerCnt = customers.Length;
		DoorMan dm = new DoorMan();

		for (int i = 0; i < customerCnt; i++)
		{
			char now = customers[i];

			if (i == 0 || i == customerCnt - 1)
			{
				//// 'DoorMan'으로 추상화하였으니 push처럼 너무 구체적인 이름보다는 'enter'등이 더 어울릴 것 같습니다 : 22
				dm.push(now);
			}
			else
			{
				char next = customers[i + 1];

				//// 어떤 의도의 조건인지 boolean 변수나 함수로 이름을 붙여줬다면 더 읽기 쉬웠을 것 같습니다 : 22
				if (dm.peek() != now || (dm.peek() == now && now == next))
				{
					dm.push(now);
				}
				else
				{
					dm.push(next);
					customers[i + 1] = now;
				}
			}

			if (dm.getGap() > maxRemember)
			{
				Console.Write(dm.size() - 1);
				return;
			}
		}
		Console.Write(dm.size());
	}
	static char[] getCustomerArr()
	{
		string input = Console.ReadLine();
		int len = input.Length;
		char[] charArr = new char[len];

		for (int i = 0; i < len; i++)
		{
			charArr[i] = input[i];
		}
		return charArr;
	}
}
public class DoorMan
{
	Stack stack = new Stack();
	int wCnt = 0, mCnt = 0;

	public void push(char c)
	{
		stack.Push(c);
		if (c == 'W') wCnt++;
		else mCnt++;
	}
	public int getGap()
	{
		return Math.Abs(wCnt - mCnt);
	}
	public char peek()
	{
		return Convert.ToChar(stack.Peek());
	}
	public int size()
	{
		return stack.Count;
	}
}