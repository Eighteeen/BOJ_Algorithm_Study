const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1
5 3 6
0 2
1 2
2 2
3 2
4 2
4 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const traversalInCabbagesByBFS = (cabbages, startX, startY) => {
  let queue = [[startX, startY]];
  let queueCursor = 0;

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= cabbages.length || ny < 0 || ny >= cabbages[0].length) continue;
      if (!cabbages[nx][ny]) continue;

      cabbages[nx][ny] = false;
      queue.push([nx, ny]);
    }
  }
};

const getLeastWorms = (cabbages) => {
  let cntOfWorms = 0;

  for (let i = 0; i < cabbages.length; i++) {
    for (let j = 0; j < cabbages[0].length; j++) {
      if (!cabbages[i][j]) continue;

      cntOfWorms += 1;
      traversalInCabbagesByBFS(cabbages, i, j);
    }
  }

  return cntOfWorms;
};

const T = parseInt(input());
for (let i = 0; i < T; i++) {
  const [M, N, K] = input().split(' ').map(Number);
  const cabbages = Array.from(new Array(M), () => new Array(N).fill(false));

  for (let j = 0; j < K; j++) {
    const [X, Y] = input().split(' ').map(Number);
    cabbages[X][Y] = true;
  }

  console.log(getLeastWorms(cabbages));
}
