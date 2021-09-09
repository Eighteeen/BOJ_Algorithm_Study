const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 3 2
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
-1 -1 -1 -1
1 1 1 -1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getDayOfTomatoesRipeByBFS = () => {
  let queueCursor = 0;
  let queue = [];

  for (let i = 0; i < H; i++) {
    for (let j = 0; j < N; j++) {
      for (let k = 0; k < M; k++) {
        if (boxOfTomatoes[i][j][k] === 1) queue.push([i, j, k]);
      }
    }
  }

  let dx = [0, 0, 1, -1, 0, 0];
  let dy = [1, -1, 0, 0, 0, 0];
  let dz = [0, 0, 0, 0, 1, -1];

  while (queue.length > queueCursor) {
    let [x, y, z] = queue[queueCursor++];

    for (let i = 0; i < 6; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];
      let nz = z + dz[i];

      if (nx < 0 || nx >= H || ny < 0 || ny >= N || nz < 0 || nz >= M) continue;
      if (boxOfTomatoes[nx][ny][nz]) continue;

      boxOfTomatoes[nx][ny][nz] = boxOfTomatoes[x][y][z] + 1;
      queue.push([nx, ny, nz]);
    }
  }

  let dayOfTomatoesRipe = 0;
  for (let i = 0; i < H; i++) {
    for (let j = 0; j < N; j++) {
      for (let k = 0; k < M; k++) {
        if (boxOfTomatoes[i][j][k] === 0) return -1;
        if (dayOfTomatoesRipe < boxOfTomatoes[i][j][k]) dayOfTomatoesRipe = boxOfTomatoes[i][j][k];
      }
    }
  }

  return dayOfTomatoesRipe - 1;
};

const [M, N, H] = input().split(' ').map(Number);
let boxOfTomatoes = Array.from(new Array(H), () => new Array());

for (let i = 0; i < H; i++) {
  for (let j = 0; j < N; j++) {
    boxOfTomatoes[i][j] = input().split(' ').map(Number);
  }
}

console.log(getDayOfTomatoesRipeByBFS());

//// 깔끔