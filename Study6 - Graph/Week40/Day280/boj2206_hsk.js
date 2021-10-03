const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 4
0100
1110
1000
0000
0111
0000`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getShortestDistance = (startX, startY, isBroke) => {
  const shortestDistance = Array.from(new Array(N), () => new Array());
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      shortestDistance[i][j] = new Array(2).fill(0);
    }
  }

  const dx = [0, 0, 1, -1];
  const dy = [1, -1, 0, 0];
  const queue = [[startX, startY, isBroke]];
  let queueCursor = 0;

  shortestDistance[startX][startY][isBroke] = 1;

  while (queue.length > queueCursor) {
    const [x, y, isBroke] = queue[queueCursor++];

    if (x === N - 1 && y === M - 1) return shortestDistance[x][y][isBroke];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

      let moveWithoutBrokeWall = !map[nx][ny] && !shortestDistance[nx][ny][isBroke];
      if (moveWithoutBrokeWall) {
        queue.push([nx, ny, isBroke]);
        shortestDistance[nx][ny][isBroke] = shortestDistance[x][y][isBroke] + 1;
      }

      let moveBrokeWall = map[nx][ny] && !isBroke;
      if (moveBrokeWall) {
        queue.push([nx, ny, isBroke + 1]);
        shortestDistance[nx][ny][isBroke + 1] = shortestDistance[x][y][isBroke] + 1;
      }
    }
  }

  return -1;
};

const [N, M] = input().split(' ').map(Number);
const map = Array.from(new Array(N), () => input().split('').map(Number));

console.log(getShortestDistance(0, 0, 0));
