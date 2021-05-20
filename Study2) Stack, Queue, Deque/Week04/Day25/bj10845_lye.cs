using System;
using System.Text;
using System.Collections.Generic;

public class Test
{
  public static void Main()
  {
    int line = Convert.ToInt32(Console.ReadLine());
    //// 스택은 배열로 구현하셨지 않나요? 배열로는 구현을 못해서 List를 쓰신건가요..?
    //// -> List가 더 편할거 같아서 List로 구현했씁니다. 
    List<string> queue = new List<string>();
    StringBuilder sb = new StringBuilder();

    //// Select Case문, 함수, 클래스, 클래스+Reflection 등 코드를 정돈할 방법은 많습니다 : 22 어느저도 정돈이 필요해보여요!
    for (int i = 0; i < line; i++)
    {
      string[] input = Read_ints();

      if (input[0] == "push")
        queue.Add(input[1]);

      if (input[0] == "pop")
      {
        if (queue.Count == 0)
          sb.Append("-1\n");
        else
        {
          sb.Append(queue[0] + "\n");
          queue.RemoveAt(0);
        }
      }
      if (input[0] == "size")
        sb.Append(queue.Count + "\n");

      if (input[0] == "empty")
      {
        if (queue.Count == 0)
          sb.Append("1\n");
        else
          sb.Append("0\n");
      }
      if (input[0] == "front")
      {
        if (queue.Count == 0)
          sb.Append("-1\n");
        else
          sb.Append(queue[0] + "\n");
      }
      if (input[0] == "back")
      {
        if (queue.Count == 0)
          sb.Append("-1\n");
        else
          sb.Append(queue[queue.Count - 1] + "\n");
      }
    }
    Console.Write(sb);
  }
  static string[] Read_ints()
  {
    string[] strArr = Console.ReadLine().Split();
    return strArr;
  }
}
