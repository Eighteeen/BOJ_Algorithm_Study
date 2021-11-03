const fs = require('fs');
const N = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `10`).split('\n');

const factorial = (num) => {
  if (num <= 1) return 1;

  return factorial(num - 1) * num;
};

console.log(factorial(N));
//// ㄲㄲ