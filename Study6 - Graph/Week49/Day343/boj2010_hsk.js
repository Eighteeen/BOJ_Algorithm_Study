const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
5
8`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
let totalOfComputer = 0;
for (let i = 0; i < N; i++) {
  totalOfComputer += parseInt(input());
}
console.log(totalOfComputer - (N - 1));
//// ㄲㄲ