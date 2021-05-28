const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `32 16 8`).split(' ');

const A = parseInt(input[0]);
const B = parseInt(input[1]);
const C = parseInt(input[2]);

//// 아~ Math.max 사용할 수도 있네요
console.log(parseInt(Math.max((A * B) / C, (A / B) * C)));
