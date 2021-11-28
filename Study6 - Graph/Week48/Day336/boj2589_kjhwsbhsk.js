const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMaxOfLeastDistance = (startX, startY) => {
  const distance = Array.from(Array(col), () => new Array(row).fill(0));
  const queue = [[startX, startY]];
  let queueCursor = 0;
  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];
  let maxOfLeastDistance = 0;

  distance[startX][startY] = 1;

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      maxOfLeastDistance = Math.max(maxOfLeastDistance, distance[x][y]);

      if (nx < 0 || ny < 0 || nx >= col || ny >= row) continue;
      if (map[nx][ny] === 'L' && distance[nx][ny] === 0) {
        queue.push([nx, ny]);
        distance[nx][ny] = distance[x][y] + 1;
      }
    }
  }
  return maxOfLeastDistance - 1;
};

const [col, row] = input().split(' ').map(Number);
const map = Array.from(Array(col), () => input().split(''));

let maxOfLeastDistance = 0;
for (let i = 0; i < col; i++) {
  for (let j = 0; j < row; j++) {
    if (map[i][j] === 'L') {
      maxOfLeastDistance = Math.max(maxOfLeastDistance, getMaxOfLeastDistance(i, j));
    }
  }
}
console.log(maxOfLeastDistance);
