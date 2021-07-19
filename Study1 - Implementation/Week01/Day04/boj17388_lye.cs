using System;

//// 굳 되게 깔끔한 것 같습니다
public class Test
{
	public static void Main()
	{
		int[] arr = Read_ints();

		//// 참여 대학이 늘어나면 코드를 하나하나 고쳐야 해서 유지보수와 활용성면에서 아쉬워요. C#에서는 List Sum() 함수를 쉽게 쓸 수 있는 걸로 알고 있어요.
		//// Sum() 함수를 사용해서 코드를 변경하시면 유지보수와 활용성면에서 더 좋아질 걸로 보여요
		if (arr[0] + arr[1] + arr[2] > 99)
		{
			Console.WriteLine("OK");
		}
		else
		{
			//// Sum()과 마찬가지로 C#에서는 List Min() 함수를 쉽게 쓸 수 있는 걸로 알고 있어요. Min() 함수를 이용하시면 유지보수와 활용성면에서 더 좋아질 것 같아요
			if (arr[0] < arr[1] && arr[0] < arr[2])
				Console.WriteLine("Soongsil");
			if (arr[1] < arr[0] && arr[1] < arr[2])
				Console.WriteLine("Korea");
			if (arr[2] < arr[0] && arr[2] < arr[1])
				Console.WriteLine("Hanyang");
		}

	}

	//// 나중에 다른 문제에서도 써먹을 수 있게 3개 이상도 불러올 수 있게하면 어떨까요
	public static int[] Read_ints()
	{
		string input;
		int[] res = new int[3];
		input = Console.ReadLine();
		string[] tmp = input.Split(' ');

		for (int i = 0; i < 3; i++)
		{
			res[i] = Convert.ToInt32(tmp[i]);
		}
		return res;
	}
}