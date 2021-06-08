const fs = require('fs');
const [A, B] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `-2147483647 1`)
  .split(' ')
  .map((item) => Number(item));

const max = Math.max(A, B);
const min = Math.min(A, B);

//// 깔끔! js 부럽다! : 22 깔끔!!
const sigmaFormula = (max, min) => {
  return ((max + min) * (max - min + 1)) / 2;
};

console.log(sigmaFormula(max, min));
