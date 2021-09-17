const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `17`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const T = parseInt(input());
console.log(Math.round(Math.pow(T / 2, 2)));

//// 깔끔