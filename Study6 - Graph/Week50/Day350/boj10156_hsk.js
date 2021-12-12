const fs = require('fs');
const [K, N, M] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `20 10 320`)
  .split(' ')
  .map(Number);

const totalPrice = K * N;
console.log(totalPrice > M ? totalPrice - M : 0);
