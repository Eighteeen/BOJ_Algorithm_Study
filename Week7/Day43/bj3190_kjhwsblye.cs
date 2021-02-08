using System;
using System.Collections;
using System.Collections.Generic;

public enum Directions
	{
		colPlus,
		rowPlus,
		colMinus,
		rowMinus
	}
public class Test
{
	public static void Main()
	{
		int boardSize = ReadInt();
		int appleCnt = ReadInt();
		List<Point> applePos = new List<Point>();
		Queue snake = new Queue();
		Point head = new Point(1,1);
		Direction dr = new Direction();
		
		for(int i=0; i<appleCnt; i++)
		{
			applePos.Add(new Point(ReadIntArr()));
		}
		int changeCnt = ReadInt();
		
		Queue changeSecond = new Queue();
		Queue changeDirection = new Queue();
		for(int i=0; i<changeCnt; i++)
		{
			string[] input = Console.ReadLine().Split(' ');
			changeSecond.Enqueue(Convert.ToInt32(input[0]));
			changeDirection.Enqueue(input[1]);
		}
		int second = 0;
		while(true)
		{
			second++;
			snake.Enqueue(head);
			head.ChangePoint(dr.getDirection());
			if(head.GetRow() > boardSize || head.GetCol() > boardSize) break;
			if(IsBody(snake, head)) break;
			int nowApple = applePos.IndexOf(head);
			if(nowApple > -1)
			{
				applePos.RemoveAt(nowApple);
				snake.Enqueue(head);
				head.ChangePoint(dr.getDirection());
			}
			else
			{
				snake.Dequeue();
			}
			if(changeSecond.Count != 0 && second == Convert.ToInt32(changeSecond.Peek()))
			{
				dr.GetType().GetMethod(changeDirection.Dequeue().ToString()).Invoke(dr,null);
				changeSecond.Dequeue();
			}
		}
		Console.Write(second);
	}
	static bool IsBody(Queue que, Point p)
	{
		List<Point> clone = new List<Point>(que);
		return clone.IndexOf(p) > -1;
	}
	static int ReadInt()
	{
		return Convert.ToInt32(Console.ReadLine());
	}
	static int[] ReadIntArr()
	{
		string[] input = Console.ReadLine().Split(' ');
		int len = input.Length;
		int[] numArr = new int[len];
		for(int i=0; i<len; i++)
		{
			numArr[i] = Convert.ToInt32(input[i]);
		}
		return numArr;
	}
}
public class Point
{
	int row,col;
	public Point(int[] input)
	{
		row = input[0];
		col = input[1];
	}
	public Point(int r, int c)
	{
		row = r;
		col = c;
	}
	public void ChangePoint(Directions dr)
	{
		switch(dr)
			{
				case Directions.colPlus:
					col++;
				break;
				case Directions.rowPlus:
					row++;
				break;
				case Directions.colMinus:
					col--;
				break;
				case Directions.rowMinus:
					row--;
				break;
			}
	}
	public int GetRow()
	{
		return row;
	}
	public int GetCol()
	{
		return col;
	}
}
public class Direction
{
	Directions dr = Directions.colPlus;
	int drSize = Directions.GetValues().Length;
	
	public void D()
	{
		int res = Convert.ToInt32(dr);
		res = (res + 1) % drSize;
		dr = res;
	}
	public void L()
	{
		int res = Convert.ToInt32(dr);
		res = (res - 1 +  drSize) % drSize;
		dr = res;
	}
	public Directions getDirection()
	{
		return dr;
	}
}