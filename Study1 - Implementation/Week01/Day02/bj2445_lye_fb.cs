using System;

public class Test
{
    static void Stamp_star(int n)
    {
        for (int j = 0; j < n; j++)
            Console.Write("*");
    }
    static void Stamp_blank(int n)
    {
        for (int j = 0; j < n; j++)
            Console.Write(" ");
    }

    public static void Main()
    {
        string input;
        input = Console.ReadLine();
        int n = Convert.ToInt32(input);
        int double_n = n * 2;

        for (int i = 1; i < double_n; i++)
        {
            if (i < n + 1)
            {
                Stamp_star(i);
                Stamp_blank(double_n- 2 * i);
                Stamp_star(i);
            }
            else
            {
                Stamp_star(double_n - i);
                Stamp_blank(2 * i - double_n);
                Stamp_star(double_n - i);
            }
            Console.Write("\n");
        }
    }
}