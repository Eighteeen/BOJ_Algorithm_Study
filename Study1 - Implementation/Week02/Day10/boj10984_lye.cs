using System;
using System.Text;

//// 전체적으로 깔끔합니다
public class Test
{
	public static void Main()
	{
		int caseNum = Read_int();
		//// major이라는 단어는 전공이라는 뜻 때문에 변수 오해의 소지가 있어보여요
		int scoreNum, sumMajor;
		double multiValue, major, score;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < caseNum; i++)
		{
			scoreNum = Read_int();
			sumMajor = 0;
			multiValue = 0;

			for (int j = 0; j < scoreNum; j++)
			{
				double[] majorScore = Read_doubles();
				major = majorScore[0];
				score = majorScore[1];
				sumMajor += Convert.ToInt32(major);
				multiValue += major * score;
			}

			double result = Math.Round(multiValue / sumMajor, 1);
			sb.Append(sumMajor + " " + result * 1.0 + "\n");
		}
		Console.Write(sb);
	}

	static int Read_int()
	{
		string input = Console.ReadLine();
		return Convert.ToInt32(input);
	}

	static double[] Read_doubles()
	{
		string input = Console.ReadLine();
		string[] strArr = input.Split();
		double[] doubleArr = new double[strArr.Length];
		for (int i = 0; i < strArr.Length; i++)
			doubleArr[i] = Convert.ToDouble(strArr[i]);
		return doubleArr;
	}
}