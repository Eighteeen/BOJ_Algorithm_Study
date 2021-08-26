const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 4
---|
---|
---|
----`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

// 기본 풀이
const getCntOfWoodenPlanks = () => {
  let numOfWoodenPlanks = 0;

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      numOfWoodenPlanks += 1;
      if (matrix[i][j] === '-' && j > 0 && matrix[i][j - 1] === '-') numOfWoodenPlanks -= 1;
      if (matrix[i][j] === '|' && i > 0 && matrix[i - 1][j] === '|') numOfWoodenPlanks -= 1;
    }
  }

  return numOfWoodenPlanks;
};

const [N, M] = input().split(' ').map(Number);
const matrix = Array.from(new Array(N), () => input().split(''));

console.log(getCntOfWoodenPlanks());
