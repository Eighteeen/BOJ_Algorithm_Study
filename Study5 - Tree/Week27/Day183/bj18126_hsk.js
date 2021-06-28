const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
1 2 3
2 3 2
1 4 9
1 5 10
4 6 20
4 7 10
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

    let length = 0;
    length = node.length + findLongLengthStreet(node.connectedNode);
    maxLength = Math.max(maxLength, length);

    visitedNode[node.connectedNode] = false;
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

//실패원인 : 1번 노드를 방문한 것으로 표현하지 않아서 계속 실패함!
visitedNode[1] = true;
console.log(findLongLengthStreet(1));
