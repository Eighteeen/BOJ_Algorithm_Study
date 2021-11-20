const fs = require('fs');
const [A, B] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `100 40021`)
  .split(' ')
  .map(Number);

const getLeastCntOfOperation = (numOfStart, numOfTarget) => {
  const queue = [[numOfStart, 0]];

  while (queue.length) {
    const [num, cntOfOperation] = queue.shift();

    if (num === numOfTarget) return cntOfOperation + 1;

    for (let i = 0; i < 2; i++) {
      let nextNum = 0;

      if (i === 0) nextNum = 2 * num;
      else nextNum = 10 * num + 1;

      if (nextNum <= numOfTarget) {
        queue.push([nextNum, cntOfOperation + 1]); //// 부럽다..
      }
    }
  }
  return -1;
};

console.log(getLeastCntOfOperation(A, B));
//// 깔끔