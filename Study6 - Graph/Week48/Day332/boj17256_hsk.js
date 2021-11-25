const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `15 8 15
22 8 22`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [ax, ay, az] = input().split(' ').map(Number);
const [cx, cy, cz] = input().split(' ').map(Number);

console.log(cx - az, parseInt(cy / ay), cz - ax);
//// ㄲㄲ : 22