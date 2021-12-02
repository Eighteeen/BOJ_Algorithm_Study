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

if (remainder === F) {
  console.log('00');
} else if (remainder >= 10) {
  console.group(remainder);
} else {
  console.log('0' + remainder);
}
