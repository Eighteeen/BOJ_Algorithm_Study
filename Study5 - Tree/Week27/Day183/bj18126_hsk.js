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

//// 깔꼼합니다!
const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => Array());
const visitedNode = new Array(N + 1).fill(false);

//// visited가 kjh, wsb 코드의 '자식에게서 부모와의 연결을 끊는 부분'과 중복된 방문을 막는다는 점에서 비슷한 역할을 하는 것 같은데
//// 제가 생각해낼 수 없는 방식으로 구현한 코드를 보니 신기하네요.
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
