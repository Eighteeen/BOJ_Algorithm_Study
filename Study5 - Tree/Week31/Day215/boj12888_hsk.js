const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `60`).split('\n');
const H = Number(input[0]);
const numOfCarNeeded = [1, 1];

for (let i = 2; i <= H; i++) {
  if (i % 2 === 0) {
    numOfCarNeeded[i] = 2 * numOfCarNeeded[i - 1] + 1;
  } else {
    numOfCarNeeded[i] = 2 * numOfCarNeeded[i - 1] - 1;
  }
}

console.log(Number(numOfCarNeeded[H]));
