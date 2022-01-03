const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9 12
.###.#####..
#.oo#...#v#.
#..o#.#.#.#.
#..##o#...#.
#.#v#o###.#.
#..#v#....#.
#...v#v####.
.####.#vv.o#
.......####.`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setTotalServivingSheepAndWolves = (startX, startY) => {
  let sheep = 0;
  let wolves = 0;
  if (yard[startX][startY] === SHEEP) {
    sheep += 1;
  }
  if (yard[startX][startY] === WOLF) {
    wolves += 1;
  }

  visited[startX][startY] = true;

  const queue = [[startX, startY]];
  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];
  while (queue.length) {
    const [x, y] = queue.shift();
    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
      if (yard[nx][ny] === FENCE || visited[nx][ny]) continue;

      if (yard[nx][ny] === SHEEP) {
        sheep += 1;
      }
      if (yard[nx][ny] === WOLF) {
        wolves += 1;
      }

      visited[nx][ny] = true;
      queue.push([nx, ny]);
    }
  }

  if (sheep > wolves) {
    totalSurvivingSheep += sheep;
  } else {
    totalWolves += wolves;
  }
};

const [EMPTY, FENCE, SHEEP, WOLF] = ['.', '#', 'o', 'v'];
const [R, C] = input().split(' ').map(Number);
const yard = Array.from(new Array(R), () => input().split(''));
const visited = Array.from(new Array(R), () => new Array(C).fill(false));

let totalSurvivingSheep = 0;
let totalWolves = 0;
for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (!visited[i][j] && yard[i][j] !== FENCE) {
      setTotalServivingSheepAndWolves(i, j);
    }
  }
}
console.log(totalSurvivingSheep, totalWolves);
//// ㄲㄲ : 22