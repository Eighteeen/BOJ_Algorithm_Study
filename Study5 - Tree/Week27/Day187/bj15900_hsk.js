//// 문제 실패 : RangeError: Maximum call stack size exceeded 에러가 나오지만 원인을 찾지 못함
const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `8
8 1
1 4
7 4
6 4
6 5
1 3
2 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const calcDepthFromRoot = (nodeNum, depth) => {
  let connectedNodeList = tree[nodeNum];
  if (connectedNodeList.length === 1 && nodeNum !== 1) {
    sumDepthFromRoot += depth;
    return;
  }

  for (let node of connectedNodeList) {
    if (!visitedNode[nodeNum]) {
      visitedNode[nodeNum] = true;
      calcDepthFromRoot(node, depth + 1);
      visitedNode[nodeNum] = false;
    }
  }
};

const isWinGame = (num) => {
  if (num % 2 !== 0) return true;
  return false;
};

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => Array());
const visitedNode = new Array(N + 1).fill(false);
let sumDepthFromRoot = 0;

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input()
    .split(' ')
    .map((value) => parseInt(value));
  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

calcDepthFromRoot(1, 0);
console.log(isWinGame(sumDepthFromRoot) ? 'Yes' : 'No');
