const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 5
.S...
...S.
S....
...S.
.S...`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 그래프 활용이 드러나지 않아 아쉽긴 하지만 그래프 이론 지식을 이용한 거라고 생각해요 깔끔합니다.
const [R, C] = input().split(' ').map(Number);
const pasture = Array.from(new Array(R), () => input().split(''));
let isSheepSafeInPasture = true;

for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (pasture[i][j] === 'S') continue;

    if (pasture[i][j] === 'W') {
      //// dx dy 이열~ : 22 이열~~
      let dx = [-1, 1, 0, 0];
      let dy = [0, 0, -1, 1];

      for (let k = 0; k < 4; k++) {
        let nx = i + dx[k];
        let ny = j + dy[k];

        if (nx >= 0 && nx < R && ny >= 0 && ny < C && pasture[nx][ny] == 'S') {
          isSheepSafeInPasture = false;
          break;
        }
      }
    } else {
      pasture[i][j] = 'D';
    }
  }
}

if (!isSheepSafeInPasture) console.log(0);
else {
  console.log(1);
  console.log(pasture.map((item) => item.join('')).join('\n'));
}
