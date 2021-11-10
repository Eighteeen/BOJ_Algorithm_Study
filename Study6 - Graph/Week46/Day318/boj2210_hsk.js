const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 2 1
1 1 1 1 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const addMakedNums = (startX, startY) => {
  const queue = [[startX, startY, board[startX][startY]]];
  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length) {
    const [x, y, num] = queue.shift();

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

      let connectedNum = num + board[nx][ny];
      if (connectedNum.length === 6) {
        numsOfMaked.add(connectedNum);
      } else {
        queue.push([nx, ny, connectedNum]);
      }
    }
  }
};

const board = Array.from(Array(5), () => input().split(' '));
let numsOfMaked = new Set([]);

for (let i = 0; i < 5; i++) {
  for (let j = 0; j < 5; j++) {
    addMakedNums(i, j);
  }
}
console.log(numsOfMaked.size);
