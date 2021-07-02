const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `10
8 1
1 4
7 4
6 4
6 5
1 3
2 3
5 9
5 10`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const calcDepthFromRoot = (nodeNum, depth) => {
  let connectedNodeList = tree[nodeNum];
  if (connectedNodeList.length === 1 && nodeNum !== 1) return depth;

  let sumDepthFromRoot = 0;
  for (let node of connectedNodeList) {
    if (visitedNode[node]) continue;

    visitedNode[node] = true;
    sumDepthFromRoot += calcDepthFromRoot(node, depth + 1);
  }

  return sumDepthFromRoot;
};

const isWinGame = (num) => {
  if (num % 2 === 0) return false;
  return true;
};

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => Array());
const visitedNode = new Array(N + 1).fill(false);

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input()
    .split(' ')
    .map((nodeNum) => parseInt(nodeNum));
  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

visitedNode[1] = true;
let sumDepth = calcDepthFromRoot(1, 0);
console.log(isWinGame(sumDepth) ? 'Yes' : 'No');
