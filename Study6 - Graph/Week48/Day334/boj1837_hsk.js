const fs = require('fs');
const [P, K] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `77 12`)
  .split(' ')
  .map((value) => BigInt(value));

for (let i = BigInt(2); i < K; i++) {
  if (P % i === BigInt(0)) {
    console.log(`BAD ${i}`);
    process.exit(0);
  }
}

console.log('GOOD');
