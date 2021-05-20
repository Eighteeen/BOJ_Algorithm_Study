/* 제가 처음에 짠 코드입니다. 메모리를 여기서 더 어떻게 줄여야할지 전혀 모르겠어서 서칭해서 Stream이란걸 */
/* 머리털나고 처음으로 알게 되었습니다. 사용해서 코드를 짰는데 사용법도 확실치 않고 출력이 안나와서  */
/* 끝끝내 포기하고 답안을 봤습니다. 알고보니 Close를 안해줘서 그런거였습니다... */
/* 이렇게나 메모리 다이어트를 한건 처음이었지만 험난했지만 유익했습니다. */
using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int line = Convert.ToInt32(Console.ReadLine());
		int[] numArr = new int[10001];
		int num;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line; i++)
		{
			num = Convert.ToInt32(Console.ReadLine());
			numArr[num] = numArr[num] + 1;
		}

		for (int i = 0; i < numArr.Length; i++)
		{
			if (numArr[i] > 0)
			{
				for (int j = 0; j < numArr[i]; j++)
				{
					sb.Append(i).Append("\n");
				}
			}
		}

		Console.Write(sb);
	}

}