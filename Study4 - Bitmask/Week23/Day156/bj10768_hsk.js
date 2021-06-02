const fs = require('fs');
const input = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
18`
).split('\n');

const month = parseInt(input[0]);
const day = parseInt(input[1]);

//// 깔꼼~ : 22 무난~
if (month === 1 || (month === 2 && day < 18)) {
  console.log('Before');
} else if (month === 2 && day === 18) {
  console.log('Special');
} else console.log('After');
