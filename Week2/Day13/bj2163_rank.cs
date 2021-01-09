// https://www.acmicpc.net/source/24248193
using System;

namespace _2163
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] input = Console.ReadLine().Split(' ');
            int N = int.Parse(input[0]);
            int M = int.Parse(input[1]);

            Console.WriteLine(N * M - 1);
               
        }
    }
}