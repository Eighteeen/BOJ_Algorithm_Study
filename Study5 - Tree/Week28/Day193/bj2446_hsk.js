const fs = require('fs');
let stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5`).split('\n');

const N = parseInt(stdin);

const drawStar = (spaceCnt, starCnt) => {
  return ' '.repeat(spaceCnt) + '*'.repeat(starCnt);
};

for (let i = N; i > 0; i--) {
  console.log(drawStar(N - i, 2 * i - 1));
}
for (let i = 2; i <= N; i++) {
  console.log(drawStar(N - i, 2 * i - 1));
}

//// 깔끔~