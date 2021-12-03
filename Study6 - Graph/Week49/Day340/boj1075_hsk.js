const fs = require('fs');
const [N, F] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1000
3`
)
  .split('\n')
  .map(Number);

const numToBeRemoved = Math.floor(N / 100) * 100;
const remainder = F - (numToBeRemoved % F);

//// 팁: console.log(String(remainder).padStart(2, '0')); 한줄로 가능!
if (remainder === F) {
  console.log('00');
} else if (remainder >= 10) {
  console.group(remainder);
} else {
  console.log('0' + remainder);
}
//// 깔끔