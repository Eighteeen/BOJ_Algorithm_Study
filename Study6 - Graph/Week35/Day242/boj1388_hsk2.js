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

// hsk1.js 로 풀고 나서 방문을 체크하는 것으로 그래프를 최대한 활용해보았는데
// 코드만 더 복잡해지고 알아보기 힘든 것 같다고 생각이 드네요,,
//// 그러게요. 그래프로도 해본거 리스펙!
//// 그래프 이론과 문제의 특성을 적절하게 로직에 잘 활용하신 것 같아요! 깔끔합니다.
const getCntOfWoodenPlanks = () => {
  const visitedMatrix = Array.from(new Array(N), () => new Array(M).fill(false));
  let numOfWoodenPlanks = 0;

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (visitedMatrix[i][j]) continue;
      numOfWoodenPlanks += 1;

      if (matrix[i][j] === '-') {
        let idx = j;
        while (idx < M && matrix[i][idx] === '-') {
          visitedMatrix[i][idx] = true;
          idx += 1;
        }
      } else {
        let idx = i;
        while (idx < N && matrix[idx][j] === '|') {
          visitedMatrix[idx][j] = true;
          idx += 1;
        }
      }
    }
  }

  return numOfWoodenPlanks;
};

const [N, M] = input().split(' ').map(Number);
const matrix = Array.from(new Array(N), () => input().split(''));

console.log(getCntOfWoodenPlanks());
