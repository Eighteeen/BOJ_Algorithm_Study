const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
0 0 1 1 1
0 0 2 0 2
1 2 0 1 3
1 0 1 0 1
1 2 3 1 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getBitmaskOrOperation = (numArr) => {
  let bitmask = 0;
  for (let num of numArr) {
    bitmask |= num;
  }
  return bitmask;
};

const N = parseInt(input());
const result = [];
for (let i = 0; i < N; i++) {
  const numArr = input()
    .split(' ')
    .map((item) => parseInt(item));
  result.push(getBitmaskOrOperation(numArr));
}

console.log(result.join(' '));
