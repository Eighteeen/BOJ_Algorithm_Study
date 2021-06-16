const fs = require('fs');
let [N, W, H, L] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `7 17 11 5`)
  .split(' ')
  .map((item) => parseInt(item));

//// 깔껌 : 22
const cowCount = parseInt(W / L) * parseInt(H / L);
const maxCowCount = Math.min(N, cowCount);
console.log(maxCowCount);
