const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 19
4 4
23 14
0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

while (1) {
  const [numA, numB] = input().split(' ').map(Number);
  if (numA === 0 && numB === 0) break;

  console.log(numA > numB ? 'Yes' : 'No');
}
//// 깔끔 :2