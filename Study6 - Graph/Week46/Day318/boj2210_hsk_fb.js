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

  //// BFS 까리하네요. 생각도 못했는데 배워갑니다 : 22 깔끔하게 구현됐네요
  while (queue.length) {
    const [x, y, numStr] = queue.shift();

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

      //// 변수명이 num이라서 숫자 연산을 하는 것처럼 느껴져요.
      //// Str 등으로 의미를 덧붙여서 문자열 변수라는 것을 알려주면 좋을 것 같아요.
      //// => 반영했어용
      let connectedNum = numStr + board[nx][ny];
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
