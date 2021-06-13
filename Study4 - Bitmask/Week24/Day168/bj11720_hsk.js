const fs = require('fs');
let [N, numStr] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
54321`
).split('\n');

const numArr = numStr.split('').map((item) => Number(item));
const sumNum = numArr.reduce((sum, currValue) => {
  return sum + currValue;
}, 0);

console.log(sumNum);
