const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `8 19
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 0
0 0 1 0 1 0 0 1 1 0 0 1 0 0 0 1 0 0 0
0 1 0 0 0 1 0 1 0 1 0 1 0 0 0 1 0 0 0
0 1 1 1 1 1 0 1 0 1 0 1 0 0 0 1 0 0 0
0 1 0 0 0 1 0 1 0 0 1 1 0 0 0 1 0 0 0
0 1 0 0 0 1 0 1 0 0 0 1 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const traversalInBannerByBFS = (startX, startY) => {
  let queue = [[startX, startY]];
  let queueCursor = 0;

  let dx = [0, 0, 1, -1, 1, 1, -1, -1];
  let dy = [1, -1, 0, 0, -1, 1, 1, -1];

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    for (let i = 0; i < 8; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
      if (!banner[nx][ny]) continue;

      banner[nx][ny] = 0;
      queue.push([nx, ny]);
    }
  }
};

const [M, N] = input().split(' ').map(Number);
const banner = Array.from(new Array(M), () => input().split(' ').map(Number));
let cntOfWord = 0;

for (let i = 0; i < M; i++) {
  for (let j = 0; j < N; j++) {
    if (!banner[i][j]) continue;

    cntOfWord += 1;
    traversalInBannerByBFS(i, j);
  }
}

console.log(cntOfWord);
