const fs = require('fs');
let N = Number(process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1000`);

let result = 0;
N = N % 8;

if (N === 1) result = 1;
else if (N === 2 || N === 0) result = 2;
else if (N === 3 || N === 7) result = 3;
else if (N === 4 || N === 6) result = 4;
else result = 5;

console.log(result);
//// ㄲㄲ