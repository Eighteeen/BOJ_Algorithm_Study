using System;
using System.Linq;
using System.Text;
using System.Collections;

//// 무난하게 잘 짜신 것 같습니다 큐 활용이 좋네용
public class Test
{
	public static void Main()
	{
		int[] input = Read_ints();
		int numCnt = input[0];
		int removeIndex = input[1];
		string res = "";
		Queue queue = new Queue();
		int cnt = 0, nowPop;

		for (int i = 0; i < numCnt; i++)
		{
			//// for문의 초기화식과 조건식을 i = 1; i <= numCnt로 하면 덧셈연산은 추가로 하지 않아도 괜찮습니다 : 22
			queue.Enqueue(i + 1);
		}
		while (queue.Count != 0)
		{
			//// removeIndex만큼 Dequeue하는 로직으로 이해되는데, for문으로 구현했으면 더 읽기 편했을 것 같습니다.
			//// cnt++은 증감식, cnt <= removeIndex가 조건식, cnt = 0이 초기화식. 이렇게 for문의 요소가 분산되어있네요
			cnt++;
			nowPop = Convert.ToInt32(queue.Dequeue());
			if (cnt == removeIndex)
			{
				//// StringBuilder를 사용했다면 더 효율적이였을 것 같아요. 문자열과 문자열을 연결하는 + 연산은 오버헤드가 꽤 있다고 합니다.
				res += nowPop;
				//// while의 조건문을 'queue의 아이템수가 1개보다 많을때'로 했다면 if문을 추가로 쓰지 않아도 괜찮습니다
				if (queue.Count != 0) res += ", ";
				cnt = 0;
			}
			else
				queue.Enqueue(nowPop);
		}

		Console.Write("<" + res + ">");
	}
	static int[] Read_ints()
	{
		string[] strArr = Console.ReadLine().Split(' ');
		int len = strArr.Length;
		int[] numArr = new int[len];
		numArr[0] = Convert.ToInt32(strArr[0]);
		numArr[1] = Convert.ToInt32(strArr[1]);
		return numArr;
	}
}