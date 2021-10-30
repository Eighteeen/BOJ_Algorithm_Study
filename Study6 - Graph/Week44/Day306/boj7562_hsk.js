const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
8
0 0
7 0
100
0 0
30 50
10
1 1
1 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMinDistance = (N, [startX, startY], [endX, endY]) => {
  const cntOfDistance = Array.from(Array(N), () => Array(N).fill(0));
  const dx = [2, 1, -2, -1, -2, -1, 1, 2];
  const dy = [1, 2, 1, 2, -1, -2, -2, -1];
  let queue = [[startX, startY]];
  let queueCursor = 0;

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    if (x === endX && y === endY) return cntOfDistance[x][y];

    for (let i = 0; i < 8; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      if (cntOfDistance[nx][ny]) continue;

      cntOfDistance[nx][ny] = cntOfDistance[x][y] + 1;
      queue.push([nx, ny]);
    }
  }
};

const TEST_CASE = Number(input());
for (let i = 0; i < TEST_CASE; i++) {
  const lenOfChessBoard = Number(input());
  const startPoint = input().split(' ').map(Number);
  const endPoint = input().split(' ').map(Number);

  const cntOfMinDistanceInChessboard = getMinDistance(lenOfChessBoard, startPoint, endPoint);
  console.log(cntOfMinDistanceInChessboard);
}
//// 깔끔 :2