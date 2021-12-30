const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
0 0 0
0 0 0
0 0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const countWaysToMovePipe = (x, y, pipe) => {
  if (x === N - 1 && y === N - 1) {
    cntOfWaysToMovePipe += 1;
    return;
  }

  for (let i = 0; i < 3; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    let isMoveEachHorizontalAndVertical = (i === 0 && pipe === 1) || (i === 1 && pipe === 0);
    if (isMoveEachHorizontalAndVertical) {
      continue;
    }

    let isOutOfIdxOrIsWall = nx >= N || ny >= N || house[nx][ny];
    if (isOutOfIdxOrIsWall) {
      continue;
    }

    let ifPipeIsDiagonal = i === 2 && (house[x][y + 1] || house[x + 1][y]);
    if (ifPipeIsDiagonal) {
      continue;
    }

    countWaysToMovePipe(nx, ny, i);
  }
};

const N = parseInt(input());
const house = Array.from(new Array(N), () => input().split(' ').map(Number));
const dx = [0, 1, 1];
const dy = [1, 0, 1];

let cntOfWaysToMovePipe = 0;
countWaysToMovePipe(0, 1, 0);

console.log(cntOfWaysToMovePipe);
//// 깔끔 : 22