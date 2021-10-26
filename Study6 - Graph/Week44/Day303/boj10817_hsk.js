const fs = require('fs');
const nums = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `20 10 10`)
  .split(' ')
  .map(Number);

nums.sort((a, b) => b - a);

console.log(nums[1]);
