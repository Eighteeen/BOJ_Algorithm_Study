const fs = require('fs');
const [UR, TR, UO, TO] = (
  process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1 10 100 1000`
).split(' ');

console.log(56 * UR + 24 * TR + 14 * UO + 6 * TO);
//// 깔끔 :2