const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `13
8`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const PI = 3.141592;
const d1 = Number(input());
const d2 = Number(input());

console.log(2 * d1 + 2 * PI * d2);
