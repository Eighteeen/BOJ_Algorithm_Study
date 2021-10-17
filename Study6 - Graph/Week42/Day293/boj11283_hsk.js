const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `준`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const word = input();
console.log(word.charCodeAt() - '가'.charCodeAt() + 1);
//// 깔끔