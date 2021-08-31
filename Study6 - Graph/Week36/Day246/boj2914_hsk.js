const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `10 10`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [A, I] = input().split(' ').map(Number);
console.log(A * (I - 1) + 1);
//// 깔끔.. : 22