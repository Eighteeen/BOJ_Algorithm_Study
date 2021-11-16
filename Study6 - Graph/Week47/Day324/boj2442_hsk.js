const fs = require('fs');
const N = Number(process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5`);

for (let i = 1; i <= N; i++) {
  console.log(' '.repeat(N - i) + '*'.repeat(2 * i - 1));
}
