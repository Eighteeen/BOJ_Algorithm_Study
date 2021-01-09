/* 제가 원래 짠 코드입니다. 뭔가 아주 단단히 이상하죠? 문제를 잘못 이해하고 이렇게 짜고선 왜 안되는지 계속 찾고있었어요,,, */
/* 모범답안을 보고나서 엥? 이게 왜 정답인거지 ?? 하고 의문을 품었다가 보니까 제가 문제를 잘못 이해했다는 것을 알게 되었어요,,,*/
/* 저는 문제 첫줄에 나오는 숫자가 녹음된 소리가 몇줄 나온다는 것으로 이해하고 코드를 짰어요 그래놓고 왜 안되나 하고 있었어요 */
/* 근데 알고보니 녹음된 소리는 무조건 한줄로 나오고 맨 첫줄에 나오는 숫자는 테스테 케이스의 개수였더라구요 저는 테스트 케이스 개수를 녹음되는 줄로 이해했어요 */
/* 아주 많은 시간을 허무하게 소요한만큼 다음부턴 문제를 제발 제발 제발 잘 읽자는 교훈을 얻었습니다.....*/
using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;

public class Test
{
	public static void Main()
	{
		int record_line = Read_int();
		List<string> record = Read_record(record_line);

		List<string> anim = Animal_sound();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < anim.Count; i++)
		{
			record.RemoveAll(d => d.Equals(anim[i]));
		}
		for (int i = 0; i < record.Count; i++)
		{
			if (record[i] == "V") sb.Append("\n");
			else sb.Append(record[i] + " ");
		}
		Console.Write(sb);

	}
	static List<string> Read_record(int repeat)
	{
		string input = "";
		for (int i = 0; i < repeat; i++)
		{
			input += Console.ReadLine();
			if (i != repeat - 1) input += " V ";
		}
		List<string> rec = input.Split(' ').ToList();
		return rec;
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static List<string> Animal_sound()
	{
		string input = Console.ReadLine();
		int sound;
		List<string> animal = new List<string>();

		while (input != "what does the fox say?")
		{
			string[] strArr = input.Split(' ');
			sound = Array.IndexOf(strArr, "goes") + 1;
			animal.Add(strArr[sound]);
			input = Console.ReadLine();
		}
		return animal;
	}
}