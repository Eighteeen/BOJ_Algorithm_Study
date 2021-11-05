const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 1
0
2 2
0 1
1 0
3 2
1 1 1
1 1 1
5 4
1 0 1 0 0
1 0 0 0 0
1 0 1 0 1
1 0 0 1 0
5 4
1 1 1 0 1
1 0 1 0 1
1 0 1 0 1
1 0 1 1 1
5 5
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 함수 첫 단어는 동사로 해주세요!
//// => 반영했습니다.
const searchInMap = (map, startX, startY, row, col) => {
  const queue = [[startX, startY]];
  let queueCursor = 0;

  let dx = [0, 0, 1, -1, 1, 1, -1, -1];
  let dy = [1, -1, 0, 0, -1, 1, 1, -1];

  while (queue.length > queueCursor) {
    const [x, y] = queue[queueCursor++];

    for (let i = 0; i < 8; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
      if (!map[nx][ny]) continue;

      map[nx][ny] = 0;
      queue.push([nx, ny]);
    }
  }
};

while (1) {
  const [width, height] = input().split(' ').map(Number);
  const map = Array.from(Array(height), () => Array(width));

  if (width === 0 && height === 0) break;

  for (let i = 0; i < height; i++) {
    map[i] = input().split(' ').map(Number);
  }

  let cntOfIsland = 0;
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      if (!map[i][j]) continue;

      cntOfIsland += 1;
      searchInMap(map, i, j, height, width);
    }
  }

  console.log(cntOfIsland);
}
//// 깔끔
