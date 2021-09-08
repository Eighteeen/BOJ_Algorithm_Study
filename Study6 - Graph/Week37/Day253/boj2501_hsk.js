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
//// ㄲㄲ : 22
//// K 가 작은 숫자일 때 최적화가 이루어지지 않아서 아쉽기는 합니다.
//// => 두분 코드 보고 오~ 했습니다. 덕분에 배워가요
