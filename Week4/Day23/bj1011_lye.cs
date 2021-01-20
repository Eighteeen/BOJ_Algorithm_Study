using System;
using System.Text;

//// 전체적으로 깔끔하게 잘 짜신 것 같습니다!
public class Test
{
	public static void Main()
	{
		long caseCnt = Convert.ToInt32(Console.ReadLine());
		long distance, sqrtDistance, sqrtSquare;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < caseCnt; i++)
		{
			distance = GetDistance();
			sqrtDistance = Convert.ToInt64(Math.Sqrt(distance));
			sqrtSquare = Convert.ToInt64(Math.Pow(sqrtDistance, 2));
			sb.Append(GetMoveCnt(sqrtDistance, sqrtSquare, distance))
				.Append("\n");
		}
		Console.Write(sb);
	}
	static long GetDistance()
	{
		string[] strArr = Console.ReadLine().Split();
		long a = Convert.ToInt64(strArr[0]);
		long b = Convert.ToInt64(strArr[1]);
		return b - a;
	}
	static long GetMoveCnt(long sqrt, long square, long origin)
	{
		long result = square - Convert.ToInt64(Math.Pow(sqrt - 1, 2));
		if (square - (sqrt - 1) <= origin && origin <= square)
			return result;
		else
			return result + 1;
	}
}