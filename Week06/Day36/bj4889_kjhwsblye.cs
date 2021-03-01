using System;
using System.Collections;
using System.Text;

public class Test
{
	public static void Main()
	{
		string input;
		StringBuilder sb = new StringBuilder(); 
		int nthCase = 1;
		
		while((input = Console.ReadLine())[0] != '-')
		{
			Stack st = new Stack();
			int cnt = 0;
			
			foreach(char c in input)
			{
				if(c == '{')
				{
					st.Push(c);
					continue;
				}

				if(st.Count == 0)
				{
					st.Push('{');
					cnt++;
				}
				else
                {
                    st.Pop();
                }	
			}
			cnt += st.Count/2;
            
			sb.Append(nthCase++).Append(". ").Append(cnt).Append("\n");
		}
		Console.Write(sb);
	}
}                   