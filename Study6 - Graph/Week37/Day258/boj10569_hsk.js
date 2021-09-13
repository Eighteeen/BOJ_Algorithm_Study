const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
8 12
4 6`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const T = parseInt(input());
for (let i = 0; i < T; i++) {
  const [V, E] = input().split(' ').map(Number);
  console.log(2 - V + E);
}
//// ㄲㄲ :22