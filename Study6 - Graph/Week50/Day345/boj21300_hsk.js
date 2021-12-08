const fs = require('fs');
const bottles = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `0 0 0 23 3 100`)
  .split(' ')
  .map(Number);

//// 이욜 reduce 사용~
const totalOfBottles = bottles.reduce((sum, curVal) => {
  return sum + curVal;
}, 0);
console.log(totalOfBottles * 5);
//// ㄲㄲ : 22