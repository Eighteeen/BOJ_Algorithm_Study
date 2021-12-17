// 문제 실패 : 반례가 떠오르지 않음
// 같은 코드로 python으로 제출했을 시 정답
const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
0 - 1
- 1 -
1 - 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const calcByOperation = (preVal, curVal, op) => {
  let result = 0;
  if (op === '+') {
    result = preVal + curVal;
  } else if (op === '-') {
    result = preVal - curVal;
  } else if (op === '*') {
    result = preVal * curVal;
  }
  return result;
};

const setMinMaxValueInMap = (x, y, curResult, operation) => {
  if (x === N - 1 && y === N - 1) {
    maxOfCalculation = Math.max(maxOfCalculation, curResult);
    minOfCalculation = Math.min(minOfCalculation, curResult);
    return;
  }

  for (let i = 0; i < 2; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

    let isNumber = !isNaN(map[nx][ny]);
    if (isNumber) {
      let calculationResult = calcByOperation(curResult, parseInt(map[nx][ny]), operation);
      setMinMaxValueInMap(nx, ny, calculationResult, operation);
    } else {
      setMinMaxValueInMap(nx, ny, curResult, map[nx][ny]);
    }
  }
};

const N = parseInt(input());
const map = Array.from(Array(N), () => input().split(' '));
let [maxOfCalculation, minOfCalculation] = [-Number.MAX_VALUE, Number.MAX_VALUE];
let dx = [1, 0];
let dy = [0, 1];

setMinMaxValueInMap(0, 0, parseInt(map[0][0]), '');
console.log(maxOfCalculation, minOfCalculation);
