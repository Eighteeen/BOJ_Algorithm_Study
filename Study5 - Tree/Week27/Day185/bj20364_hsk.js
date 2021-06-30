const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 4
2
5
4
3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getVisitedNodeNum = (nodeNum) => {
  if (nodeNum < 2) return 0;
  if (visitedNodeList.has(nodeNum)) return nodeNum;

  return getVisitedNodeNum(parseInt(nodeNum / 2));
};

const [node, ducks] = input()
  .split(' ')
  .map((value) => parseInt(value));
const visitedNodeList = new Set();
let result = [];

for (let i = 0; i < ducks; i++) {
  const wantNodeNum = parseInt(input());

  let visitedNodeNum = getVisitedNodeNum(wantNodeNum);
  visitedNodeList.add(wantNodeNum);

  result.push(visitedNodeNum);
}

console.log(result.join('\n'));
