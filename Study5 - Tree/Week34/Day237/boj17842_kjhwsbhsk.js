const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9
1 2
6 7
8 2
4 5
3 4
2 6
0 3
5 8`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const degreeCntOfNodeList = new Array(N).fill(0);
for (let i = 0; i < N - 1; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  degreeCntOfNodeList[nodeA] += 1;
  degreeCntOfNodeList[nodeB] += 1;
}

let leafNodeCnt = 0;
for (let i = 0; i < N; i++) {
  if (degreeCntOfNodeList[i] === 1) leafNodeCnt += 1;
}

let lines = parseInt(leafNodeCnt / 2) + (leafNodeCnt % 2);
console.log(lines);
