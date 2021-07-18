const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `19
0 1 2 1 3 4 3 5 3 1 6 1 0 7 8 7 9 7 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const visitedNodeArr = input().split(' ').map(Number);
const parentNodeArr = [];
const nodeNumInTree = new Set();

parentNodeArr[visitedNodeArr[0]] = -1;
nodeNumInTree.add(visitedNodeArr[0]);

for (let i = 0; i < N; i++) {
  if (nodeNumInTree.has(visitedNodeArr[i])) continue;

  parentNodeArr[visitedNodeArr[i]] = visitedNodeArr[i - 1];
  nodeNumInTree.add(visitedNodeArr[i]);
}

console.log(nodeNumInTree.size);
console.log(parentNodeArr.join(' '));
