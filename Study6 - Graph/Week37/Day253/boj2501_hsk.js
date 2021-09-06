const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `2735 1`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [N, K] = input().split(' ').map(Number);
const divisorArr = [];
for (let i = 1; i <= N; i++) {
  if (N % i !== 0) continue;

  divisorArr.push(i);
}

console.log(divisorArr[K - 1] ? divisorArr[K - 1] : 0);
