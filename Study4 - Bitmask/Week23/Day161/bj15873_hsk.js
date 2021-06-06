const fs = require('fs');
const input = process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `102`;

const A = parseInt(input.length >= 3 && input[1] === '0' ? input.substr(0, 2) : input.substr(0, 1));
const B = parseInt(A === 10 ? input.substr(2) : input.substr(1));

console.log(A + B);
