const fs = require('fs');
let stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `12`).split('\n');

const N = parseInt(stdin[0]);
let result = 1;

//// Clean
if (N % 2 === 1) result = 0;
else if (parseInt(N / 2) % 2 === 0) result = 2;

console.log(result);
