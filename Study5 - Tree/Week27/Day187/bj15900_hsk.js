const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
2 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const calcDepthFromRoot = (nodeAndDepthArr) => {
  while (nodeAndDepthArr.length > 0) {
    let [nodeNum, depth] = nodeAndDepthArr.pop();
    visitedNode[nodeNum] = true;

    let connectedNodeList = tree[nodeNum];
    if (connectedNodeList.length === 1 && nodeNum !== 1) {
      depthFromRootList.push(depth);
      continue;
    }

    connectedNodeList.forEach((node) => {
      if (!visitedNode[node]) nodeAndDepthArr.push([node, depth + 1]);
    });
  }
};

const sumDepthFromRoot = (depthArr) => {
  return depthArr.reduce((sum, curr) => sum + curr, 0);
};

const isWinGame = (num) => {
  if (num % 2 !== 0) return true;
  return false;
};

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => Array());
const visitedNode = new Array(N + 1).fill(false);
let depthFromRootList = [];
let nodeAndDepthArr = [[1, 0]];

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input()
    .split(' ')
    .map((value) => parseInt(value));
  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

calcDepthFromRoot(nodeAndDepthArr);

let sumDepth = sumDepthFromRoot(depthFromRootList);
console.log(isWinGame(sumDepth) ? 'Yes' : 'No');
