const fs = require('fs');
const num = process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `314`;

console.log(parseInt(num, 8).toString(2));
