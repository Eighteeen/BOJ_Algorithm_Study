using System;
using System.Collections.Generic;
using System.Linq;

const int MinSize = 8;
var size = Console.ReadLine().Split();
var h = int.Parse(size[0]);
var w = int.Parse(size[1]);

var chess1 = new string[]
{
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
};

var chess2 = new string[]
{
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
    "BWBWBWBW",
    "WBWBWBWB",
};

var inputChess = new string[h];
for (var i = 0; i < h; i++)
{
    inputChess[i] = Console.ReadLine();
}

var list = new List<int>();
for (var y = 0; y <= h - MinSize; y++)
{
    for (var x = 0; x <= w - MinSize; x++)
    {
        var c1 = Solve(inputChess, chess1, x, y);
        var c2 = Solve(inputChess, chess2, x, y);
        list.Add(c1 < c2 ? c1 : c2);
    }
}

Console.WriteLine(list.Min());

static int Solve(string[] chess, string[] check, int startX, int startY)
{
    var count = 0;
    for (var i = 0; i < MinSize; i++)
    {
        var y = startY + i;
        for (var j = 0; j < MinSize; j++)
        {
            var x = startX + j;
            if (chess[y][x] != check[i][j])
            {
                count++;
            }
        }
    }

    return count;
}

