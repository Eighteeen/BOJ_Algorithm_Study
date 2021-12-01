const fs = require('fs');
const [N, M] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `-2 5`).split(' ');

console.log(Math.abs(N - M));
