const fs = require('fs');
const [N, K] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `8 9`)
  .split(' ')
  .map((item) => parseInt(item));

//// 깔끔
let maxResult = 0;
for (let i = 1; i <= K; i++) {
  const currResult = (N * i).toString().split('');
  const reverseResult = currResult.reverse();
  maxResult = Math.max(maxResult, parseInt(reverseResult.join('')));
}
console.log(maxResult);
