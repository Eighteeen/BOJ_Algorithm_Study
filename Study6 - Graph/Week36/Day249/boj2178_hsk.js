const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7 7
1011111
1110001
1000001
1000001
1000001
1000001
1111111`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMinDistanceByBFS = (startX, startY) => {
  let cntOfDistance = Array.from(new Array(N + 1), () => new Array(M + 1).fill(0));
  let queue = [[startX, startY]];

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  cntOfDistance[startX][startY] = 1;

  while (queue.length) {
    let [x, y] = queue.shift();

    if (x === N && y === M) return cntOfDistance[x][y];

    for (let i = 0; i < 4; i++) {
      //// 그냥 호기심인데 n은 어떤거의 약자로 생각하고 쓴거에요? : 22 now 인 것 같은데 조금은 풀어쓰는 게 가독성에 더 좋을 것 같아요
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
      if (cntOfDistance[nx][ny] || !maze[nx][ny]) continue;

      cntOfDistance[nx][ny] = cntOfDistance[x][y] + 1;
      queue.push([nx, ny]);
    }
  }
};

const [N, M] = input().split(' ').map(Number);
let maze = Array.from(new Array(N + 1), () => new Array(M + 1));

for (let i = 1; i <= N; i++) {
  //// 요건 뭐 어떻게 돌아가는거에요??
  maze[i] = [0].concat(input().split('').map(Number));
}

console.log(getMinDistanceByBFS(1, 1));
//// 깔끔합니다 : 22