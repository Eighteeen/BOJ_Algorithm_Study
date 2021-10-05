const fs = require('fs');
const [N, ...nums] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
1
7
99
321
777
0`
).split('\n');

const isMultiple = nums.map((value) => value % N === 0);
for (let i = 0; i < isMultiple.length - 1; i++) {
  console.log(`${nums[i]} is${isMultiple[i] ? '' : ' NOT'} a multiple of ${N}.`);
}
//// 왕 깔끔 :2