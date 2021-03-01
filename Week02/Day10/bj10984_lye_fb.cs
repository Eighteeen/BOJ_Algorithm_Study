using System;
using System.Text;

public class Test
{
	public static void Main()
	{
		int caseNum = Read_int();
		int scoreNum, sumSubject;
		double multiValue, subject, score;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < caseNum; i++)
		{
			scoreNum = Read_int();
			sumSubject = 0;
			multiValue = 0;

			for (int j = 0; j < scoreNum; j++)
			{
				double[] subjectScore = Read_doubles();
				subject = subjectScore[0];
				score = subjectScore[1];
				sumSubject += Convert.ToInt32(subject);
				multiValue += subject * score;
			}

			double result = Math.Round(multiValue / sumSubject, 1);
			sb.Append(sumSubject + " " + result * 1.0 + "\n");
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