const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 6
....#.
.#....
..#..#
####..`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getLenOfBeach = () => {
  let lenOfBeach = 0;

  let dx = [-1, -1, 0, 0, 1, 1];
  let dy_odd = [0, 1, -1, 1, 0, 1];
  let dy_even = [-1, 0, -1, 1, -1, 0];

  for (let x = 0; x < N; x++) {
    for (let y = 0; y < M; y++) {
      if (map[x][y] === '.') continue;

      for (let i = 0; i < 6; i++) {
        let nx = x + dx[i];
        let ny = y;

        if (x % 2 !== 0) ny += dy_odd[i];
        else ny += dy_even[i];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
        if (map[nx][ny] === '#') continue;

        lenOfBeach += 1;
      }
    }
  }

  return lenOfBeach;
};

const [N, M] = input().split(' ').map(Number);
const map = Array.from(new Array(N), () => input().split(''));

console.log(getLenOfBeach());
//// 깔끔 : 22