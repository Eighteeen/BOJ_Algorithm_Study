const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
10
30
25
16`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const TEST_CASE = parseInt(input());
for (let i = 0; i < TEST_CASE; i++) {
  console.log('='.repeat(parseInt(input())));
}
