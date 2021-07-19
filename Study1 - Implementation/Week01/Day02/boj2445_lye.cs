using System;

public class Test
{
    //// star, blank를 s, b와 같이 줄여쓰니 무얼 의미하는지 직관적으로 알기가 어려운 것 같습니다:22
    public void Stamp_s(int n)
    {
        for (int j = 0; j < n; j++)
            Console.Write("*");
    }
    public void Stamp_b(int n)
    {
        for (int j = 0; j < n; j++)
            Console.Write(" ");
    }

    public static void Main()
    {
        string input;
        input = Console.ReadLine();
        int n = Convert.ToInt32(input);
        //// 메소드를 static으로 선언하면 인스턴스화를 하지 않아도 바로 쓸 수 있습니다
        Test ts = new Test();

        //// 2 * n 라는 연산을 자주 쓰네요? 루프가 실행될 때마다 연산하지 않게끔 변수 할당을 하면 좋을 것 같아요
        for (int i = 1; i < 2 * n; i++)
        {
            if (i < n + 1)
            {
                ts.Stamp_s(i);
                ts.Stamp_b(2 * n - 2 * i);
                ts.Stamp_s(i);
                //// 메소드를 활용하셔서 직관적으로 보이도록 하신 것 같아요 굿
            }
            else
            {
                ts.Stamp_s(2 * n - i);
                ts.Stamp_b(2 * i - 2 * n);
                ts.Stamp_s(2 * n - i);
            }
            Console.Write("\n");
        }
    }
}