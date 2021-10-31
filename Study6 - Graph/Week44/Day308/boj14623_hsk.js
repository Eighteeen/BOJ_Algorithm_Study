const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1010
11`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const binary1 = BigInt('0b' + input());
const binary2 = BigInt('0b' + input());
console.log((binary1 * binary2).toString(2).toString());
