using System;
using System.Collections.Generic;
using System.Linq;
// 저도 이 코드처럼 나올 수 있는 체스판의 경우가 두개이기 때문에 이렇게 저장해두고 비교하려고 했었습니다.
// 근데 값을 저장하고 나서 for문을 어떻게 돌려야 할지 감이 전혀 오지 않아서 혼란스러웠고 고민하다가 결국 포기하게 되었습니다.
// 저는 주어진 보드와 정해진 체스판의 차이만큼For문하고 비교해야할 숫자인 8만큼 for문을 동시에 돌려서 비교해야한다고 생각했다 보니
// 이런 코드를 작성하지 못한 것 같습니다. 이 코드를 읽고 나니 생각보다 복잡한 과정을 가진 문제는 아니었던거 같네요.
// 차이만큼 돌려주되 그 안에서 또 for문을 돌려줄 수도 있었던거 같아 보여요.생각했던 부분을 코드로 옮겨내는데에 부족함이 있었던거 같습니다.
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
// 주어진 보드 한 줄씩 입력받아 저장해주고
var inputChess = new string[h];
for (var i = 0; i < h; i++)
{
    inputChess[i] = Console.ReadLine();
}
// 주어진 보드와 체스판규격인 8과의 차이만큼 for문을 돌리고 정해진 체스판 두가지와 비교하여 최소값을 담아준 뒤
// 최소값들 사이에서 가장 최소값인것을 반환해주네요.
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
// 주어진 보드와 정해진 체스판을 비교해서 다른부분만큼 더해주고 그 값을 반환하네요.
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

