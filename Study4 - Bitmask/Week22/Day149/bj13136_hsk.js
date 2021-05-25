const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `7 9 3`).split(' ');

const [row, col, N] = input.map((value) => Number(value));

console.log(Math.ceil(row / N) * Math.ceil(col / N));
