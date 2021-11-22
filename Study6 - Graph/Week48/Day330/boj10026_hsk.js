const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
RRRBB
GGBBB
BBBRR
BBRRR
RRRRR`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const searchInBoard = (board, startX, startY, color) => {
  const queue = [[startX, startY]];
  let queueCursor = 0;
  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  visited[startX][startY] = true;

  while (queue.length > queueCursor) {
    const [x, y] = queue[queueCursor++];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
      if (!visited[nx][ny] && board[nx][ny] === color) {
        visited[nx][ny] = true;
        queue.push([nx, ny]);
      }
    }
  }
};

const N = parseInt(input());
const board = Array.from(new Array(N), () => input().split(''));
const boardRGWeakness = Array.from(new Array(N), () => new Array(N).fill('B'));
let visited = Array.from(new Array(N), () => new Array(N).fill(false));

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (board[i][j] === 'R' || board[i][j] === 'G') boardRGWeakness[i][j] = 'R';
  }
}

let cntOfSectionInBoard = 0;
for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (!visited[i][j]) {
      searchInBoard(board, i, j, board[i][j]);
      cntOfSectionInBoard += 1;
    }
  }
}

visited = Array.from(new Array(N), () => new Array(N).fill(false));
let cntOfSectionInboardRGWeakness = 0;
for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (!visited[i][j]) {
      searchInBoard(boardRGWeakness, i, j, boardRGWeakness[i][j]);
      cntOfSectionInboardRGWeakness += 1;
    }
  }
}

console.log(cntOfSectionInBoard, cntOfSectionInboardRGWeakness);
