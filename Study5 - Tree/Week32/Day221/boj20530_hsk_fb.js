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

//// 깔끔
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

//// 아래 * 변수명 관련 피드백 *과 비슷한 이유로 함수명이 어색하게 해석됩니다.
//// 'LCA가 rootNum인지 세팅한다.' 로 해석되어서 boolean값들을 세팅하는 함수처럼 느껴져요.
const setLCAOfTreeThatRootIsCycleNode = (root) => {
  const stackNodeList = [root];
  const visitedNodeList = new Set();

  visitedNodeList.add(root);
  LCAOfTreeThatRootIsCycleNode[root] = root;

  while (stackNodeList.length) {
    let node = stackNodeList.pop();

    for (let nextNode of graph[node]) {
      if (cycleNodeList.has(nextNode) || visitedNodeList.has(nextNode)) continue;

      visitedNodeList.add(nextNode);
      LCAOfTreeThatRootIsCycleNode[nextNode] = root;
      stackNodeList.push(nextNode);
    }
  }
};

const [N, Q] = input().split(' ').map(Number);
const graph = Array.from(new Array(N + 1), () => new Array());
const degreeCntOfNodeList = new Array(N + 1).fill(0);
const cycleNodeList = new Set(new Array(N).fill(0).map((val, idx) => idx + 1));
//// * 변수명 관련 피드백 *
//// 끝이 IsRoot로 끝나니까 boolean 요소들이 들어있을 것 같은 느낌이에요.
//// idx num과 싸이클 노드와의 LCA니까, LCAWithCycleNode, LCABetweenCycleNide 정도는 어떤가요?
//// 아니면 저같은 경우는 LCA라고 생각하지 않고 그냥 Sub Tree로 생각했는데, rootOfCycleSubTree 이런식으로도 괜찮을 것 같아요!
//// => cycle node is root : 사이클 노드가 루트 라고 해석하는게 보편적이라고 생각합니다.
//// => isRoot를 맨 앞에 썼으면 boolean이라고 생각하겠지만 보통 명사가 가장 앞인 경우는 변수도 명사라고 생각합니다.
//// => 그렇지만 의견 반영하여 LCAOfTreeThatRootIsCycleNode 이렇게 변경하겠습니다.
const LCAOfTreeThatRootIsCycleNode = new Array(N + 1).fill(0);

for (let i = 1; i <= N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  graph[nodeA].push(nodeB);
  graph[nodeB].push(nodeA);

  degreeCntOfNodeList[nodeA] += 1;
  degreeCntOfNodeList[nodeB] += 1;
}

setCycleNodeList();
setLCAOfTreeThatRootIsCycleNode();

let result = [];
for (let i = 0; i < Q; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  result.push(LCAOfTreeThatRootIsCycleNode[nodeA] === LCAOfTreeThatRootIsCycleNode[nodeB] ? 1 : 2);
}

console.log(result.join('\n'));

//setLCAIsRootNumOfCycleNodeIsRoot() 함수 안에서
//visitedNodeList를 배열로 평소처럼 new Array(N + 1).fill(false); 로 선언하여
//dfs를 구현하였지만 시간초과가 남
//=>원인
//=>값을 추가나 변경 때마다 해당 값이 이미 있는지 확인하는
//=>기존 방식은 매우 큰 오버헤드를 발생시킨다.
//=>라고 한다... ㅠㅠ..
