const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 2
3 4`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [A, B] = input().split(' ').map(Number);
const [C, D] = input().split(' ').map(Number);

console.log(Math.min(A + D, B + C));
//// ㄲㄲ : 22