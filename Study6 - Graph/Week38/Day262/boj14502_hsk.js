const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const buildWallByDFS = (cntOfWall) => {
  if (cntOfWall === 3) {
    spreadVirusInMap();
    return;
  }

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (map[i][j] === BLANK) {
        map[i][j] = WALL;
        buildWallByDFS(cntOfWall + 1);
        map[i][j] = BLANK;
      }
    }
  }
};

const spreadVirusInMap = () => {
  let virusMap = Array.from(new Array(N), () => new Array(M).fill(0));
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      virusMap[i][j] = map[i][j];
    }
  }

  let queue = [];
  let queueCursor = 0;

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (virusMap[i][j] === VIRUS) queue.push([i, j]);
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
      if (virusMap[nx][ny] === WALL || virusMap[nx][ny] === VIRUS) continue;

      virusMap[nx][ny] = VIRUS;
      queue.push([nx, ny]);
    }
  }

  getMaxSafetyBoundary(virusMap);
};

const getMaxSafetyBoundary = (virusMap) => {
  let cntOfBLANK = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (virusMap[i][j] === BLANK) cntOfBLANK += 1;
    }
  }

  maxSafetyBoundary = Math.max(maxSafetyBoundary, cntOfBLANK);
};

const [N, M] = input().split(' ').map(Number);
let map = Array.from(new Array(N), () => input().split(' ').map(Number));

const [BLANK, WALL, VIRUS] = [0, 1, 2];
let maxSafetyBoundary = 0;

for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (map[i][j] === BLANK) {
      map[i][j] = WALL;
      buildWallByDFS(1);
      map[i][j] = BLANK;
    }
  }
}

console.log(maxSafetyBoundary);
