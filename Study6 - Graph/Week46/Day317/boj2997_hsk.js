const fs = require('fs');
const nums = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `10 1 4`)
  .split(' ')
  .map(Number);

nums.sort((a, b) => a - b);

const diffPair1 = nums[1] - nums[0];
const diffPair2 = nums[2] - nums[1];

if (diffPair1 > diffPair2) {
  console.log(nums[0] + diffPair2);
} else if (diffPair1 < diffPair2) {
  console.log(nums[1] + diffPair1);
} else {
  console.log(nums[2] + diffPair1);
}
//// 다들 비슷하군요