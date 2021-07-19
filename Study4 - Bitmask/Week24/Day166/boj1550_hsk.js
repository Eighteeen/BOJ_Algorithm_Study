const fs = require('fs');
const strN = process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `A`;

//// 깔끔 :22
console.log(parseInt(strN, 16));
