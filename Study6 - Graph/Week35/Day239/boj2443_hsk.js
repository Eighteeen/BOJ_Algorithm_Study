const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const drawStar = (spaceCnt, starCnt) => {
  return ' '.repeat(spaceCnt) + '*'.repeat(starCnt);
};

//// 정말 깔끔하네요 :22
const N = parseInt(input());
for (let i = 0; i < N; i++) {
  console.log(drawStar(i, 2 * (N - i) - 1));
}
