const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 6
1 5
3 4
5 4
4 2
4 6
5 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setComparabletallArr = (comparableStudentNum) => {
  const visited = new Array(N + 1).fill(false);
  const queue = [comparableStudentNum];
  let queueCursor = 0;

  visited[comparableStudentNum] = true;

  while (queue.length > queueCursor) {
    const studentNum = queue[queueCursor++];

    for (let i = 1; i <= N; i++) {
      if (!visited[i] && comparableTallArr[studentNum][i]) {
        visited[i] = true;
        comparableTallArr[comparableStudentNum][i] = true;
        queue.push(i);
      }
    }
  }
};
const [N, M] = input().split(' ').map(Number);
const comparableTallArr = Array.from(Array(N + 1), () => new Array(N + 1).fill(false));
for (let i = 0; i < M; i++) {
  const [shortStudent, tallStudent] = input().split(' ').map(Number);
  comparableTallArr[shortStudent][tallStudent] = true;
}

for (let i = 1; i <= N; i++) {
  setComparabletallArr(i);
}

let totalCntOfcomparableTall = 0;
for (let studentNum = 1; studentNum <= N; studentNum++) {
  let cntOfcomparableTall = 0;
  for (let i = 1; i <= N; i++) {
    if (comparableTallArr[studentNum][i]) cntOfcomparableTall += 1;
    if (comparableTallArr[i][studentNum]) cntOfcomparableTall += 1;
  }

  if (cntOfcomparableTall == N - 1) {
    totalCntOfcomparableTall += 1;
  }
}

console.log(totalCntOfcomparableTall);
