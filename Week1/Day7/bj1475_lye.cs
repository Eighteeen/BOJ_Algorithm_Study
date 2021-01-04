using System;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int[] numbers = new int[10];
		int[] roomNum = Read_ints();
		int now, sixNineValue, result, sixAndNine;
		for (int i = 0; i < roomNum.Length; i++)
		{
			now = roomNum[i];
			numbers[now]++;
		}
		//// 6과 9가 쓰인 횟수를 일단 더하고, 2로 나눠서 홀수면 1을 더 더해준 값을 sixNineValue로, 6과 9의 개수를 0으로 해놓고 다른 숫자끼리 최대값을 구한다음에, 아까 구한 sixNineValue와 비교해서 더 큰 값을 사용.
		//// 코드를 해석하자면 위와 같은데 음.. 좀 정리가 덜 된 느낌이에요. 더 깔끔하게 구해낼 수 있을 것 같습니다.
		sixAndNine = numbers[6] + numbers[9];
		sixNineValue = sixAndNine % 2 == 0 ? sixAndNine / 2 : sixAndNine / 2 + 1;
		numbers[6] = numbers[9] = 0;
		result = sixNineValue > numbers.Max() ? sixNineValue : numbers.Max();
		Console.Write(result);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		int[] numArr = new int[input.Length];
		for (int i = 0; i < input.Length; i++)
		{
			numArr[i] = Int32.Parse(input[i].ToString());
		}
		return numArr;
	}
}