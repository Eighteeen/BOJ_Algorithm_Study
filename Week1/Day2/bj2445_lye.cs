using System;

public class Test
{
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
        Test ts = new Test();

        for (int i = 1; i < 2 * n; i++)
        {
            if (i < n + 1)
            {
                ts.Stamp_s(i);
                ts.Stamp_b(2 * n - 2 * i);
                ts.Stamp_s(i);
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