using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int repeat = Read_int();
		int H, W, customerNum, roomFloor, roomNum;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < repeat; i++)
		{
			int[] toFindInfo = Read_ints();
			H = toFindInfo[0];
			W = toFindInfo[1];
			customerNum = toFindInfo[2];

			roomFloor = customerNum % H;
			roomNum = customerNum / H;

			if (roomFloor == 0) roomFloor = H;
			else roomNum++;

			sb.Append(roomFloor)
				.Append(roomNum < 10 ? "0" : "")
				.Append(roomNum)
				.Append("\n");
		}
		Console.Write(sb);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static int[] Read_ints()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split();
		int len = strArr.Length;
		int[] numArr = new int[len];
		for (int i = 0; i < len; i++)
			numArr[i] = Convert.ToInt32(strArr[i]);
		return numArr;
	}
}

