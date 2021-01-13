using System;
using System.Collections.Generic;

public class Test
{
	public static void Main()
	{
		int persons = Read_int();
		//// 성의 첫 글자는 a~z 범위 안에 있으므로 List가 아닌 int[26]로 첫 글자의 수를 구했다면 더 효율적이었을 것 같습니다. 이렇게 하면 정렬이 필요없거든요.
		List<char> playerList = new List<char>();
		int[] howManyName = new int[persons];
		char nowName;
		int isFind;
		string result = "";

		for (int i = 0; i < persons; i++)
		{
			//// 함수 이름에 성의 "첫 글자"를 읽어온다는 게 표현됐으면 좋겠습니다.
			//// Read_lastname이라길래 성 문자열을 읽어오는 줄 알고 '왜 List<char>에 성을 넣지? char 리스트에 String을 넣으면 알아서 첫 글자만 추가되나?' 이러고 있었어요
			nowName = Read_lastName();
			isFind = playerList.IndexOf(nowName);
			if (isFind == -1)
			{
				playerList.Add(nowName);
				isFind = playerList.IndexOf(nowName);
			}
			howManyName[isFind] += 1;
		}
		for (int i = 0; i < howManyName.Length; i++)
		{
			if (howManyName[i] > 4)
				result += playerList[i];
		}
		if (result == "") Console.Write("PREDAJA");
		else Console.Write(Sort_result(result));
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}
	static char Read_lastName()
	{
		string input = Console.ReadLine();
		return input[0];
	}
	static string Sort_result(string res)
	{
		int len = res.Length;
		char[] tmp = new char[len];
		for (int i = 0; i < len; i++)
			tmp[i] = res[i];
		Array.Sort(tmp);
		res = "";
		for (int i = 0; i < len; i++)
			res += tmp[i];
		return res;
	}
}