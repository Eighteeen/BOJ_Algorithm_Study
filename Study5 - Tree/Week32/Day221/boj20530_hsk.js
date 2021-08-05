const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9 2
1 2
1 3
2 4
2 5
3 6
3 7
2 3
4 8
4 9
2 4
4 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setCycleNodeList = () => {
  const stackNodeList = [];

  for (let i = 1; i <= N; i++) {
    if (degreeCntOfNodeList[i] === 1) stackNodeList.push(i);
  }

  while (stackNodeList.length) {
    let node = stackNodeList.pop();
    cycleNodeList.delete(node);

    for (let nextNode of graph[node]) {
      degreeCntOfNodeList[nextNode] -= 1;
      if (degreeCntOfNodeList[nextNode] === 1) stackNodeList.push(nextNode);
    }
  }
};

const setLCAIsRootPerCycleNode = () => {
  for (let root of cycleNodeList) {
    setLCAIsRootNumOfCycleNodeIsRoot(root);
  }
};

const setLCAIsRootNumOfCycleNodeIsRoot = (root) => {
  const stackNodeList = [root];
  const visitedNodeList = new Set();

  visitedNodeList.add(root);
  LCAOfCycleNodeIsRoot[root] = root;

  while (stackNodeList.length) {
    let node = stackNodeList.pop();

    for (let nextNode of graph[node]) {
      if (cycleNodeList.has(nextNode) || visitedNodeList.has(nextNode)) continue;

      visitedNodeList.add(nextNode);
      LCAOfCycleNodeIsRoot[nextNode] = root;
      stackNodeList.push(nextNode);
    }
  }
};

const [N, Q] = input().split(' ').map(Number);
const graph = Array.from(new Array(N + 1), () => new Array());
const degreeCntOfNodeList = new Array(N + 1).fill(0);
const cycleNodeList = new Set(new Array(N).fill(0).map((val, idx) => idx + 1));
const LCAOfCycleNodeIsRoot = new Array(N + 1).fill(0);

for (let i = 1; i <= N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  graph[nodeA].push(nodeB);
  graph[nodeB].push(nodeA);

  degreeCntOfNodeList[nodeA] += 1;
  degreeCntOfNodeList[nodeB] += 1;
}

setCycleNodeList();
setLCAIsRootPerCycleNode();

let result = [];
for (let i = 0; i < Q; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  result.push(LCAOfCycleNodeIsRoot[nodeA] === LCAOfCycleNodeIsRoot[nodeB] ? 1 : 2);
}

console.log(result.join('\n'));

//setLCAIsRootNumOfCycleNodeIsRoot() 함수 안에서
//visitedNodeList를 배열로 평소처럼 new Array(N + 1).fill(false); 로 선언하여
//dfs를 구현하였지만 시간초과가 남
//=>원인
//=>값을 추가나 변경 때마다 해당 값이 이미 있는지 확인하는
//=>기존 방식은 매우 큰 오버헤드를 발생시킨다.
//=>라고 한다... ㅠㅠ..
