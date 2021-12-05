const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
3
0
6
4
1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let score = { apple: 0, banana: 0 };
for (let i = 3; i > 0; i--) {
  score.apple += parseInt(input()) * i;
}
for (let i = 3; i > 0; i--) {
  score.banana += parseInt(input()) * i;
}

if (score.apple > score.banana) {
  console.log('A');
} else if (score.apple < score.banana) {
  console.log('B');
} else {
  console.log('T');
}
//// ㄲㄲ