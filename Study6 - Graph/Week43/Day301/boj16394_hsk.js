const fs = require('fs');
const YEAR = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `2018`).split('\n');

console.log(YEAR - 1946);
//// ㄱㄱ ㄱㄱ : 22