const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 5
-1 1 0 0 0
0 -1 -1 -1 0
0 -1 -1 -1 0
0 -1 -1 -1 0
0 0 0 0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getDayOfTomatoesRipeByBFS = () => {
  let queueCursor = 0;
  let queue = [];

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (boxOfTomatoes[i][j] === 1) queue.push([i, j]);
    }
  }

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
      if (boxOfTomatoes[nx][ny]) continue;

      boxOfTomatoes[nx][ny] = boxOfTomatoes[x][y] + 1;
      queue.push([nx, ny]);
    }
  }

  let dayOfTomatoesRipe = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (boxOfTomatoes[i][j] === 0) return -1;
      if (dayOfTomatoesRipe < boxOfTomatoes[i][j]) dayOfTomatoesRipe = boxOfTomatoes[i][j];
    }
  }

  return dayOfTomatoesRipe - 1;
};

//// 깔끔요 : 22 완전 깔끔
const [M, N] = input().split(' ').map(Number);
let boxOfTomatoes = Array.from(new Array(N), () => input().split(' ').map(Number));

console.log(getDayOfTomatoesRipeByBFS());
