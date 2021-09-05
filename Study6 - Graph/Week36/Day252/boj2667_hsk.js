const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
0110100
0110101
1110101
0000111
0100000
0111110
0111000`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getCntOfHouseInComplexes = (startX, startY) => {
  let queue = [[startX, startY]];
  let cntOfHouse = 1;

  map[startX][startY] = 0;

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length) {
    let [x, y] = queue.shift();

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      if (!map[nx][ny]) continue;

      map[nx][ny] = 0;
      queue.push([nx, ny]);

      cntOfHouse += 1;
    }
  }

  return cntOfHouse;
};

const N = parseInt(input());
let map = Array.from(new Array(N), () => input().split('').map(Number));
let numOfComplexes = 0;
let cntOfHouseInComplexes = [];

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (!map[i][j]) continue;

    numOfComplexes += 1;
    cntOfHouseInComplexes.push(getCntOfHouseInComplexes(i, j));
  }
}

console.log(numOfComplexes);
console.log(cntOfHouseInComplexes.sort((a, b) => a - b).join('\n'));
