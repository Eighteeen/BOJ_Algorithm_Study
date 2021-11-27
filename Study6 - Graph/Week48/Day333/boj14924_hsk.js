const fs = require('fs');
const [S, T, D] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `50 75 200`)
  .split(' ')
  .map(Number);

console.log((D / (S * 2)) * T);
//// ㄲㄲ : 22