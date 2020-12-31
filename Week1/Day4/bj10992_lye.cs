using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int jul = Read_int();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < jul + 1; i++)
            {
                sb.Append(' ', jul - i);
                sb.Append('*');
                if (i == jul)
                    sb.Append('*', 2 * jul - 2);
                else if (i != 1)
                {
                    sb.Append(' ', 2 * i - 3);
                    sb.Append('*');
                }

                sb.Append('\n');
            }
            Console.Write(sb);
        }

        static int Read_int()
        {
            string input;
            input = Console.ReadLine();
            return Convert.ToInt32(input);
        }
    }
}
