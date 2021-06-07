const fs = require('fs');
const input = process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `102`;

//// 깔끔
//// Java에서 js로 전향할까 고민되네요
const A = parseInt(input.length >= 3 && input[1] === '0' ? input.substr(0, 2) : input.substr(0, 1));
const B = parseInt(A === 10 ? input.substr(2) : input.substr(1));

console.log(A + B);
