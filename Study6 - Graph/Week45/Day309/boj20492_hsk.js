const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1000`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = Number(input());
console.log(N - N * 0.22);
console.log(N * 0.8 + N * 0.2 - N * 0.2 * 0.22);
