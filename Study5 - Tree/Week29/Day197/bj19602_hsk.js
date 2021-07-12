const fs = require('fs');
const [S, M, L] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
2
1`
)
  .split('\n')
  .map((value) => parseInt(value));

const happinessScore = 1 * S + 2 * M + 3 * L;

console.log(happinessScore >= 10 ? 'happy' : 'sad');
