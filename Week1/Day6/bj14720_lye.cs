using System;
using System.Collections;

public class Test
{
	public static void Main()
	{
		int num = Read_int();
		int cnt = 0;
		int[] read = Set_queue();
		Queue numbers = new Queue();

		for (int i = 0; i < read.Length; i++)
		{
			numbers.Enqueue(read[i]);
		}

		for (int i = 0; i < num; i++)
		{
			if (Peek_int(numbers) == 0)
			{
				cnt++;
				Set_state();
				break;
			}
			numbers.Dequeue();
		}

		while (numbers.Count != 0)
		{
			numbers.Dequeue();
			if (numbers.Count != 0 && Peek_int(numbers) == state)
			{
				cnt++;
				Set_state();
			}
		}
		Console.Write(cnt);
	}

	static int Peek_int(Queue Q)
	{
		int result = Convert.ToInt32(Q.Peek());
		return result;
	}
	static int[] Set_queue()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split(' ');
		int[] intArr = new int[strArr.Length];
		for (int i = 0; i < strArr.Length; i++)
			intArr[i] = Convert.ToInt32(strArr[i]);
		return intArr;
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
	static int state = 0;
	static void Set_state()
	{
		if (state == 2)
			state = 0;
		else
			state++;
	}
}