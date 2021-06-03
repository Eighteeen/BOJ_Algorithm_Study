const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
1 2 3 4 5 6 7
13 78 39 42 54 93 86`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
for (let i = 0; i < N; i++) {
  const numArr = input()
    .split(' ')
    .map((item) => parseInt(item));

  const evenNumArr = numArr.filter((item) => item % 2 === 0);
  const evenSum = evenNumArr.reduce((sum, currValue) => {
    return sum + currValue;
  }, 0);

  console.log(evenSum, Math.min.apply(null, evenNumArr));
}
