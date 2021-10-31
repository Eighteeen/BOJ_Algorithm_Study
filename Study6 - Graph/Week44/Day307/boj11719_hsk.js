const fs = require('fs');
const input =
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString()
    : `    Hello

Baekjoon     
   Online Judge    `;

console.log(input);
//// 깔끔 :2
