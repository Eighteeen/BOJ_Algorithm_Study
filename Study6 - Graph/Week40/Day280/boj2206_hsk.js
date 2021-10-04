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

const getShortestDistance = (startX, startY, isBrokeWall) => {
  //// isBrokeWall로 초기화해서 shortestDistance 자체를 반환하는 함수가 따로 있으면 좋을 것 같아요!
  const shortestDistance = Array.from(new Array(N), () => new Array());
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      shortestDistance[i][j] = new Array(2).fill(isBrokeWall);
    }
  }

  const dx = [0, 0, 1, -1];
  const dy = [1, -1, 0, 0];
  const queue = [[startX, startY, isBrokeWall]];
  let queueCursor = 0;

  shortestDistance[startX][startY][isBrokeWall] = 1;

  while (queue.length > queueCursor) {
    const [x, y, isBrokeWall] = queue[queueCursor++];

    if (x === N - 1 && y === M - 1) return shortestDistance[x][y][isBrokeWall];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

      let moveWithoutBrokeWall = !map[nx][ny] && !shortestDistance[nx][ny][isBrokeWall];
      if (moveWithoutBrokeWall) {
        queue.push([nx, ny, isBrokeWall]);
        shortestDistance[nx][ny][isBrokeWall] = shortestDistance[x][y][isBrokeWall] + 1;
      }

      let moveBrokeWall = map[nx][ny] && !isBrokeWall;
      if (moveBrokeWall) {
        queue.push([nx, ny, isBrokeWall + 1]);
        shortestDistance[nx][ny][isBrokeWall + 1] = shortestDistance[x][y][isBrokeWall] + 1;
      }
    }
  }

  return -1;
};

const [N, M] = input().split(' ').map(Number);
const map = Array.from(new Array(N), () => input().split('').map(Number));

console.log(getShortestDistance(0, 0, 0));
//// 깔끔해요