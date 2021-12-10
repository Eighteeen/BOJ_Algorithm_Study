const fs = require('fs');
const N = Number((process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1`).split('\n'));

let row = parseInt(N / 2) + 1;
let col = N - row + 2;

console.log(row * col);
//// ㄲㄲ : 22