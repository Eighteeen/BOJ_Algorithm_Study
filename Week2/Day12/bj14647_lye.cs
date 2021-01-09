using System;

public class Test
{
  ////깔끔하네요
  public static void Main()
  {
    int[] lines = Read_numArr();
    int row = lines[0];
    int col = lines[1];
    int rowmax = 0, colmax = 0, cnt = 0, allcnt = 0, check;
    int[,] bingo = new int[row, col];


    for (int i = 0; i < row; i++)
    {
      int[] toAdd = Read_numArr();
      cnt = 0;
      for (int j = 0; j < col; j++)
      {
        //// check 변수명만으로는 어떤 역할을 하는지 알 수 없네요
        check = howManyNine(toAdd[j]);
        if (check > 0)
        {
          cnt += check;
          allcnt += check;
        }
        bingo[i, j] = toAdd[j];
      }
      //// 오호 입력받을때 행 최대값 미리 세두는거 효율적이네요! 배워가용
      if (cnt > rowmax)
        rowmax = cnt;
    }

    for (int i = 0; i < col; i++)
    {
      cnt = 0;

      for (int j = 0; j < row; j++)
      {
        check = howManyNine(bingo[j, i]);
        if (check > 0)
        {
          cnt += check;
        }
      }
      if (cnt > colmax)
        colmax = cnt;
    }
    ////삼항연산자를 좋아하시는거 같네요.
    Console.Write(rowmax > colmax ? allcnt - rowmax : allcnt - colmax);

  }

  static int[] Read_numArr()
  {
    string input = Console.ReadLine();
    string[] strArr = input.Split(' ');
    int len = strArr.Length;
    int[] numArr = new int[len];
    for (int i = 0; i < len; i++)
      numArr[i] = Convert.ToInt32(strArr[i]);
    return numArr;
  }
  static int howManyNine(int num)
  {
    string check = num.ToString();
    int cnt = 0;
    for (int i = 0; i < check.Length; i++)
    {
      if (check[i] == '9') { cnt++; }
    }
    return cnt;
  }
}