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
15 8`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMaxDepthNum = () => {
  let maxDepth = 0;
  let temp = 1;

  while (temp <= N) {
    temp <<= 1;
    maxDepth++;
  }

  return maxDepth;
};

const makeTreeByDFS = (node) => {
  const stackNodeList = [node];
  const visitedNodeList = new Array(N + 1).fill(false);

  visitedNodeList[node] = true;

  while (stackNodeList.length) {
    const node = stackNodeList.pop();

    for (let nextNode of tree[node]) {
      if (visitedNodeList[nextNode]) continue;
      visitedNodeList[nextNode] = true;

      depthNodeList[nextNode] = depthNodeList[node] + 1;
      parentsNodeList[nextNode][0] = node;

      stackNodeList.push(nextNode);
    }
  }
};

const fillParentsNodeList = () => {
  for (let i = 1; i < maxDepth; i++) {
    for (let j = 1; j <= N; j++) {
      parentsNodeList[j][i] = parentsNodeList[parentsNodeList[j][i - 1]][i - 1];
    }
  }
};

const getLCA = (nodeA, nodeB) => {
  if (depthNodeList[nodeA] > depthNodeList[nodeB]) [nodeA, nodeB] = [nodeB, nodeA];

  for (let i = maxDepth - 1; i >= 0; i--) {
    if (depthNodeList[nodeB] - depthNodeList[nodeA] >= Math.pow(2, i)) nodeB = parentsNodeList[nodeB][i];
  }

  if (nodeA === nodeB) return nodeA;

  for (let i = maxDepth - 1; i >= 0; i--) {
    if (parentsNodeList[nodeA][i] === parentsNodeList[nodeB][i]) continue;

    nodeA = parentsNodeList[nodeA][i];
    nodeB = parentsNodeList[nodeB][i];
  }

  return parentsNodeList[nodeA][0];
};

const N = parseInt(input());
const tree = Array.from(new Array(N + 1), () => new Array());

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

const maxDepth = getMaxDepthNum();
const parentsNodeList = Array.from(new Array(N + 1), () => new Array(maxDepth).fill(0));
const depthNodeList = new Array(N + 1).fill(0);

//// 깔끔합니다잉 : 22
//// 파라미터 1, 1 넣은 건 코드 수정하다가 수정이 안 된 것 같은데 맞나요?? ((1) (1, 1) 둘 다 solve 되던데 오류 없이 실행되는 거 자체가 신기하네요!)
//// => 아 네 맞아요ㅠ.. 재귀로 했다가 바꿨었네요.
makeTreeByDFS(1);
fillParentsNodeList();

const M = parseInt(input());
const result = [];
for (let i = 0; i < M; i++) {
  let [targetNodeA, targetNodeB] = input().split(' ').map(Number);
  result.push(getLCA(targetNodeA, targetNodeB));
}

console.log(result.join('\n'));
