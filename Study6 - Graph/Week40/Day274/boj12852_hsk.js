const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `572`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const calcNumByBFS = (startNum) => {
  const checkedNums = new Array(N + 1).fill(false);
  const queue = [startNum];
  let queueCursor = 0;

  checkedNums[startNum] = true;
  nextCalcResult[startNum] = startNum;

  while (queue.length > queueCursor) {
    let curNum = queue[queueCursor++];

    if (curNum === N) break;

    const nextCalcNums = [curNum * 3, curNum * 2, curNum + 1];

    for (let i = 0; i < 3; i++) {
      let nextNum = nextCalcNums[i];

      if (nextNum > N || checkedNums[nextNum]) continue;
      checkedNums[nextNum] = true;

      nextCalcResult[nextNum] = curNum;
      queue.push(nextNum);
    }
  }
};

let N = Number(input());
const nextCalcResult = new Array(N + 1).fill(0);
const result = [];

calcNumByBFS(1);

result.push(N);
while (N !== 1) {
  result.push(nextCalcResult[N]);
  N = nextCalcResult[N];
}

console.log(result.length - 1);
console.log(result.join(' '));
