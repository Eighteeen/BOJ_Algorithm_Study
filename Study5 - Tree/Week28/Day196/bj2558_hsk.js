const fs = require('fs');
let [A, B] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1
2`
)
  .split('\n')
  .map((value) => parseInt(value));

console.log(A + B);
