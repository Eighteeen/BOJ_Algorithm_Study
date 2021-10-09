const fs = require('fs');
const [A, B] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `123456789123456789123456789 987654321987654321987654321`
)
  .split(' ')
  .map(BigInt);

console.log((A + B).toString());
