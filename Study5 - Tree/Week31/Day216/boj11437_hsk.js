const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 깔끔합니다.
const getLCA = (nodeA, nodeB) => {
  let depthA = depthNodeList[nodeA];
  let depthB = depthNodeList[nodeB];

  while (depthA > depthB) {
    depthA--;
    nodeA = parentsNodeList[nodeA];
  }

  while (depthA < depthB) {
    depthB--;
    nodeB = parentsNodeList[nodeB];
  }

  while (nodeA !== nodeB) {
    nodeA = parentsNodeList[nodeA];
    nodeB = parentsNodeList[nodeB];
  }

  return nodeA;
};

//// visitedNode는 list 혹은 arr 라는 용어가 함께 있으면 좋을 것 같습니다.
//// visitedNode가 makeTree에서만 쓰이니 함수 내에서 선언하면 더욱 깔끔할 것 같습니다.
const makeTree = (root) => {
  visitedNode[root] = true;

  const queueNodeList = [root];

  while (queueNodeList.length) {
    let node = queueNodeList.shift();

    for (let nextNode of tree[node]) {
      if (visitedNode[nextNode]) continue;
      visitedNode[nextNode] = true;

      depthNodeList[nextNode] = depthNodeList[node] + 1;
      parentsNodeList[nextNode] = node;

      queueNodeList.push(nextNode);
    }
  }
};

const N = parseInt(input());
const tree = Array.from(new Array(N + 1), () => new Array());
const parentsNodeList = new Array(N + 1).fill(0);
const depthNodeList = new Array(N + 1).fill(0);
const visitedNode = new Array(N + 1).fill(false);

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

makeTree(1);

const M = parseInt(input());
const result = [];
for (let i = 0; i < M; i++) {
  let [targetNodeA, targetNodeB] = input().split(' ').map(Number);
  result.push(getLCA(targetNodeA, targetNodeB));
}

console.log(result.join('\n'));

// 지속된 실패 원인 : input()을 받을 떄 Number또는 int로 변경하지 않아
// "!=="" 연산에서 문자열과 숫자를 비교하기 때문에 모두 실패처리가 됨
