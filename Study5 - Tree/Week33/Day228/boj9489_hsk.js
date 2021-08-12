const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `10 15
1 3 4 5 8 9 15 30 31 32
12 9
3 5 6 8 9 10 13 15 16 22 23 25
10 4
1 3 4 5 8 9 15 30 31 32
0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getTargetIdxOnGrouping = (N, targetNum, nodeArr, parents) => {
  let [targetIdx, group] = [0, 0];

  parents[1] = 0;

  for (let i = 1; i <= N; i++) {
    if (nodeArr[i] === targetNum) targetIdx = i + 1;
    if (nodeArr[i] !== nodeArr[i - 1] + 1) group += 1;
    parents[i + 1] = group;
  }

  return targetIdx;
};

const getCursonCnt = (targetIdx, N, parents) => {
  let cousinCnt = 0;

  for (let i = 1; i <= N; i++) {
    let isCousin = parents[i] !== parents[targetIdx] && parents[parents[i]] === parents[parents[targetIdx]];
    if (isCousin) cousinCnt += 1;
  }

  return cousinCnt;
};

const result = [];
while (1) {
  const [N, targetNum] = input().split(' ').map(Number);
  const parents = new Array(N + 1).fill(-1);

  if (N === 0 && targetNum === 0) break;

  const nodeArr = input().split(' ').map(Number);
  const targetIdx = getTargetIdxOnGrouping(N, targetNum, nodeArr, parents);
  const cousinCnt = getCursonCnt(targetIdx, N, parents);

  result.push(cousinCnt);
}

console.log(result.join('\n'));
