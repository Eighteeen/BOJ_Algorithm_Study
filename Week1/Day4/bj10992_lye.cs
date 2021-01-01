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
            //// 정수를 읽어서 jul에 대입한다! 라는게 코드만 읽어도 보이네요. 가독성 굳!
            int jul = Read_int();
            StringBuilder sb = new StringBuilder();

            //// 규칙 유추를 잘 하신 거 같아요
            for (int i = 1; i < jul + 1; i++)
            {
                sb.Append(' ', jul - i);
                sb.Append('*');
                //// 마지막 줄을 계속해서 체크하는 방법도 있지만 마지막 줄을 따로 빼도 좋을 것 같아요 방법이 이상하다는 것은 아닙니다! :22 첫줄과 마지막줄을 반복문 안에서 체크하는게 어색하긴 합니다
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
