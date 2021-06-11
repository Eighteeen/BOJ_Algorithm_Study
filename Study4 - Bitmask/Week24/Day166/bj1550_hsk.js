const fs = require('fs');
const strN = process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `A`;

console.log(parseInt(strN, 16));
