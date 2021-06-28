//// 문제풀이 실패
const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
1 2 3
2 3 2
4 2 4
`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => Array());
const visitedNode = new Array(N + 1).fill(false);

const findLongLengthStreet = (nodeNum) => {
  let connectedNodeList = tree[nodeNum];
  if (connectedNodeList.length < 1) return 0;

  let maxLength = 0;
  for (let node of connectedNodeList) {
    if (visitedNode[node.connectedNode]) continue;
    visitedNode[node.connectedNode] = true;

    maxLength = Math.max(maxLength, node.length + findLongLengthStreet(node.connectedNode));
  }

  return maxLength;
};

if (N === 1) {
  console.log(0);
  return;
}

for (let i = 1; i < N; i++) {
  const [A, B, C] = input()
    .split(' ')
    .map((value) => Number(value));
  tree[A].push({ connectedNode: B, length: C });
  tree[B].push({ connectedNode: A, length: C });
}

console.log(findLongLengthStreet(1));
