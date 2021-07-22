const fs = require('fs');
const N = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3`).split('\n');

const sum = (n) => {
  return (n * (n + 1)) / 2;
};
console.log(sum(parseInt(N)));
/// 깔끔