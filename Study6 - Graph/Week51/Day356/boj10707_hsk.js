const fs = require('fs');
const [xFee, yBaseFee, yUsage, yFee, P] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `8
300
100
10
250`
)
  .split('\n')
  .map(Number);

const waterFeeOfX = xFee * P;
const waterFeeOfY = yUsage < P ? yBaseFee + yFee * (P - yUsage) : yBaseFee;

console.log(Math.min(waterFeeOfX, waterFeeOfY));
//// ㄲㄲ : 22