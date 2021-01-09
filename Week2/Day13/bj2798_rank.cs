// https://www.acmicpc.net/source/24531694
using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        string[] a = Console.ReadLine().Split();
        int num = int.Parse( a[0]);
        int sum = int.Parse(a[1]);
        string[] aa = Console.ReadLine().Split();
        int[] value = new int[aa.Length];
        for (int i = 0; i < aa.Length; i++)
        {
            value[i] = int.Parse(aa[i]);
        }
        int result = new int();
        for (int i = 0; i < num-2; i++)
        {
            for (int k = i+1; k < num-1; k++)
            {
                for (int j = k+1; j < num; j++)
                {
                    // 1 2 3 4 5
                    int sum2 = value[i] + value[k] + value[j];
                    if (sum2 <= sum&& sum-result > sum-sum2)
                    {
                        result = sum2;
                    }
                }
            }
        }
        Console.WriteLine(result);
    }
 
}