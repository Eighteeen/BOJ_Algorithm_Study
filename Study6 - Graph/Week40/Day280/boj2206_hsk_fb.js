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

const resetToShortDistance = (isBrokeWall) => {
  const shortestDistance = Array.from(new Array(N), () => new Array());

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      shortestDistance[i][j] = new Array(2).fill(isBrokeWall);
    }
  }

  return shortestDistance;
};

const getShortestDistance = (startX, startY, isBrokeWall) => {
  //// isBrokeWall로 초기화해서 shortestDistance 자체를 반환하는 함수가 따로 있으면 좋을 것 같아요!
  //// => 좋은 생각이에요! 감사합니다! 반영했어요~
  const shortestDistance = resetToShortDistance(isBrokeWall);

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

      //// 첫 단어가 동사고, 상태보단 동작을 표현하고 있어서 좀 함수명스러운거같슴다
      //// => 아하! 감사합니당~ 아래 변수도 같이 반영했습니다.
      let movementWithoutBrokeWall = !map[nx][ny] && !shortestDistance[nx][ny][isBrokeWall];
      if (movementWithoutBrokeWall) {
        queue.push([nx, ny, isBrokeWall]);
        shortestDistance[nx][ny][isBrokeWall] = shortestDistance[x][y][isBrokeWall] + 1;
      }

      let movementBrokeWall = map[nx][ny] && !isBrokeWall;
      if (movementBrokeWall) {
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
//// 깔끔해요 :2
