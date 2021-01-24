using System;
using System.Collections;

public class Test
{
	//// 효율적이고 좋네용
	public static void Main()
	{
		//// Main 함수에 줄바꿈이 없네요
		//// 사이사이에 줄바꿈을 적절히 삽입하면 더 보기 좋은 코드가 될 것 같습니다.
		int line = Read_int();
		int cnt = 1;
		Stack stick = new Stack();
		for (int i = 0; i < line; i++)
		{
			stick.Push(Read_int());
		}
		int max = Convert.ToInt32(stick.Pop());
		for (int i = 1; i < line; i++)
		{
			int nowStick = Convert.ToInt32(stick.Pop());
			if (nowStick > max)
			{
				cnt++;
				max = nowStick;
			}
		}
		Console.Write(cnt);
	}
	//// 함수명을 ReadInt로 짓는건 어떤가요?
	//// C#에서는 보통 함수명을 파스칼 표기법으로 짓거든요. 실제로 C# 표준 라이브러리에서도 이 규칙을 따릅니다.
	static int Read_int()
	{
		int input = Convert.ToInt32(Console.ReadLine());
		return input;
	}
}