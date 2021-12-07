const fs = require('fs');
const [N, M, K] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `4 3 2`)
  .split(' ')
  .map(Number);

console.log(Math.min(M, K) + Math.min(N - M, N - K));
//// ㄲㄲ ; 22