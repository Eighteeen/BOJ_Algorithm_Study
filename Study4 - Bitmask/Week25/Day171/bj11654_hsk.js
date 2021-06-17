const fs = require('fs');
const input = process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `9`;

//// 깔 : 22 끔
console.log(input.charCodeAt(0))