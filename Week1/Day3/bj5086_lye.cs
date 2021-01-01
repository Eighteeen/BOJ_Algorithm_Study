using System;

public class Test
{
	public static void Main()
	{
		Test ts = new Test();
		while (true)
		{
			int[] arr = ts.Read_arr();

			if (arr[0] == arr[1] && arr[0] == 0) break;
			ts.Compare(arr[0], arr[1]);
		}
	}

	//// 함수로 분리해서 main이 깔끔해보여요! 굳굳
	//// "2개의 정수를 읽는 함수"인데 이름으로는 "n개를 읽는 함수" 정도만 표현된거같아 조금 아쉬워요
	public int[] Read_arr()
	{
		string input;
		string[] arr_s = new string[2];
		int[] arr_i = new int[2];
		input = Console.ReadLine();
		arr_s = input.Split(' ');
		arr_i[0] = Convert.ToInt32(arr_s[0]);
		arr_i[1] = Convert.ToInt32(arr_s[1]);
		return arr_i;
	}

	//// aa, bb로 한 이유는 혹시 한글자 변수 피드백때문에..? 함수 매개변수로는 한글자로 좋을 것 같아요! 가독성을 크게 해치지 않으니까요
	public void Compare(int aa, int bb)
	{
		//// 조건문을 의미상으로 잘 묶으신 거 같아요. 하지만 조건문을 한번 더 돌려야 하는 게 조금 아쉽네요
		if (aa % bb == 0 || bb % aa == 0)
		{
			if (aa > bb) { Console.WriteLine("multiple"); }
			else { Console.WriteLine("factor"); }
		}
		else
		{
			Console.WriteLine("neither");
		}


	}
}