const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const drawStar = (starCnt) => {
  return '*'.repeat(starCnt);
};

const N = parseInt(input());
for (let i = N; i > 0; i--) {
  console.log(drawStar(i));
}
//// 깔끔 : 22