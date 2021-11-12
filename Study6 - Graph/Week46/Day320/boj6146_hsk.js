const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 2 7
0 2
-1 3
3 1
1 1
4 2
-1 1
2 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getLeastDistances = (startX, startY) => {
  const queue = [[startX, startY]];
  let queueCursor = 0;
  let dx = [0, 0, 1, -1];
  let dy = [-1, 1, 0, 0];

  while (queue.length > queueCursor) {
    const [x, y] = queue[queueCursor++];

    if (x === destinationX && y === destinationY) break;

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || ny < 0 || nx > 1000 || ny > 1000) continue;
      if (map[nx][ny] || map[nx][ny] === POOL_FLAG) continue;

      map[nx][ny] = map[x][y] + 1;
      queue.push([nx, ny]);
    }
  }
  return map[destinationX][destinationY];
};

let [destinationX, destinationY, N] = input().split(' ').map(Number);
destinationX += 500;
destinationY += 500;

const map = Array.from(Array(1001), () => new Array(1001).fill(0));
const POOL_FLAG = true;
for (let i = 0; i < N; i++) {
  const [poolX, poolY] = input().split(' ').map(Number);
  map[poolX + 500][poolY + 500] = POOL_FLAG;
}

console.log(getLeastDistances(500, 500));
