const fs = require('fs');
const [N, T, C, P] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `60 10 300 1000`)
  .split(' ')
  .map(Number);

console.log(Math.floor((N - 1) / T) * C * P);
