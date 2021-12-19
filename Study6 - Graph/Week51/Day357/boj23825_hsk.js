const fs = require('fs');
const [numOfblockS, numOfblockA] = (
  process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `4 5`
)
  .split(' ')
  .map(Number);

console.log(Math.floor(Math.min(numOfblockS, numOfblockA) / 2));
