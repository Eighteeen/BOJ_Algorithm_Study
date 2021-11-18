const fs = require('fs');
const N = Number(process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3`);

console.log(N * 4)
//// 까끄