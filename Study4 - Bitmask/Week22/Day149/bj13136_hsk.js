const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `7 9 3`).split(' ');

const [row, col, N] = input.map((value) => Number(value));

//// 오호 Number의 특징을 잘 활용하신 거 같아요. 깔끔!
console.log(Math.ceil(row / N) * Math.ceil(col / N));
