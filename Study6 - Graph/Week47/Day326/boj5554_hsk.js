const fs = require('fs');
const seconds = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
10
15
30`
)
  .split('\n')
  .map(Number);

const totalSeconds = seconds.reduce((sum, sec) => sum + sec);

console.log(parseInt(totalSeconds / 60));
console.log(parseInt(totalSeconds % 60));
//// 깔