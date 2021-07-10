const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 1
2 3
3 4
9 8
5 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());

for (let i = 1; i <= N; i++) {
  const [A, B] = input()
    .split(' ')
    .map((value) => parseInt(value));
  console.log(`Case #${i}: ${A + B}`);
}
