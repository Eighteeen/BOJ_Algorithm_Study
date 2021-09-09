const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 7 5
.......
...O...
....O..
.......
OO.....
OO.....`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const pushBombsCoordinatesInQueue = () => {
  for (let i = 0; i < R; i++) {
    for (let j = 0; j < C; j++) {
      if (map[i][j] !== 'O') continue;

      queue.push([i, j]);
    }
  }
};

const explodeBombs = () => {
  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    map[x][y] = '.';

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
      if (map[nx][ny] !== 'O') continue;

      map[nx][ny] = '.';
    }
  }
};

const setMapOverTime = (time) => {
  if (time === 1) {
    pushBombsCoordinatesInQueue();
  } else if (time % 2 === 1) {
    explodeBombs();
    pushBombsCoordinatesInQueue();
  } else {
    map = Array.from(new Array(R), () => new Array(C).fill('O'));
  }
};

const [R, C, N] = input().split(' ').map(Number);
let map = Array.from(new Array(R), () => input().split(''));
let queue = [];
let queueCursor = 0;

for (let i = 1; i <= N; i++) {
  setMapOverTime(i);
}

console.log(map.map((item) => item.join('')).join('\n'));
