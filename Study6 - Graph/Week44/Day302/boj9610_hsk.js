const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
0 0
0 1
1 1
3 -3
2 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = Number(input());
let [Q1, Q2, Q3, Q4, AXIS] = [0, 0, 0, 0, 0];

for (let i = 0; i < N; i++) {
  const [x, y] = input().split(' ').map(Number);

  if (x > 0 && y > 0) Q1 += 1;
  else if (x < 0 && y > 0) Q2 += 1;
  else if (x < 0 && y < 0) Q3 += 1;
  else if (x > 0 && y < 0) Q4 += 1;
  else AXIS += 1;
}

console.log(`Q1: ${Q1}`);
console.log(`Q2: ${Q2}`);
console.log(`Q3: ${Q3}`);
console.log(`Q4: ${Q4}`);
console.log(`AXIS: ${AXIS}`);
/// 끔깔