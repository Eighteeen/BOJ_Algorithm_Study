const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 4
2
5
6
5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [node, ducks] = input()
  .split(' ')
  .map((value) => parseInt(value));
const visitedNodeList = new Set();
let result = [];

for (let i = 0; i < ducks; i++) {
  const wantNodeNum = parseInt(input());

  let isVisitedNum = 0;
  let nodeNum = wantNodeNum;
  while (nodeNum > 1) {
    if (visitedNodeList.has(nodeNum)) isVisitedNum = nodeNum;
    nodeNum = parseInt(nodeNum / 2);
  }

  visitedNodeList.add(wantNodeNum);
  result.push(isVisitedNum);
}

console.log(result.join('\n'));
