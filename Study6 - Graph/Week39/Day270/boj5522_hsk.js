const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1
2
3
4
5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let sum = 0;
for (let i = 0; i < 5; i++) {
  sum += parseInt(input());
}
console.log(sum);

//// 깔끔 : 22