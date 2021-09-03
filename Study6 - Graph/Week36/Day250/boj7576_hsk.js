const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2 2
1 -1
-1 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const BFS = () => {
  let queue = [];

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (box[i][j] === 1) queue.push([i, j]);
    }
  }

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length) {
    let [x, y] = queue.shift();

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
      if (box[nx][ny]) continue;

      box[nx][ny] = box[x][y] + 1;
      queue.push([nx, ny]);
    }
  }

  let dayOfTomatoesRipe = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (box[i][j] === 0) return -1;
      if (dayOfTomatoesRipe < box[i][j]) dayOfTomatoesRipe = box[i][j];
    }
  }

  return dayOfTomatoesRipe - 1;
};

const [M, N] = input().split(' ').map(Number);
let box = Array.from(new Array(N), () => input().split(' ').map(Number));

console.log(BFS());
