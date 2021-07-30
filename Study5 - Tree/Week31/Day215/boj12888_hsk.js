const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `60`).split('\n');

let H = Number(input[0]);
let numOfCarNeeded = [1, 1];

for (let i = 2; i <= H; i++) {
  if (i % 2 === 0) {
    numOfCarNeeded[i] = numOfCarNeeded[i - 1] * 2 + 1;
  } else {
    numOfCarNeeded[i] = numOfCarNeeded[i - 1] * 2 - 1;
  }
}

console.log(Number(numOfCarNeeded[H]));
