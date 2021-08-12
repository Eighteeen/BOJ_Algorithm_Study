const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 3
1 2
2 3
3 4
6 5
1 2
2 3
3 4
4 5
5 6
6 6
1 2
2 3
1 3
4 5
5 6
6 4
0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 함수명은 isTree 정도로만 지어도 의미가 충분히 전달됐을 것 같아요! : 22 node가 트리 노드인지 판별하니까 isTreeNode도 좋을 것 같아요!
//// node 매개변수는 뭐가 들어가야 하는건지 바로 알기 어려웠는데 startNode 정도로 해주면 좋을 것 같습니다
const getIsTreeOrNot = (node, tree, visitedNodeList) => {
  let isTree = true;
  const stackNodeList = [node];

  while (stackNodeList.length) {
    const node = stackNodeList.pop();

    if (visitedNodeList[node]) isTree = false;
    visitedNodeList[node] = true;

    for (let nextNode of tree[node]) {
      if (visitedNodeList[nextNode]) continue;

      stackNodeList.push(nextNode);
    }
  }

  return isTree;
};

//// 깔끔 간단
let caseCnt = 0;
while (true) {
  caseCnt += 1;

  const [N, M] = input().split(' ').map(Number);
  const tree = Array.from(new Array(N + 1), () => new Array());
  const visitedNodeList = new Array(N + 1).fill(false);

  if (N === 0 && M === 0) break;

  for (let i = 0; i < M; i++) {
    const [nodeA, nodeB] = input().split(' ').map(Number);

    tree[nodeA].push(nodeB);
    tree[nodeB].push(nodeA);
  }

  let treeCnt = 0;
  for (let i = 1; i <= N; i++) {
    if (visitedNodeList[i]) continue;
    if (getIsTreeOrNot(i, tree, visitedNodeList)) treeCnt += 1;
  }

  if (treeCnt === 0) console.log(`Case ${caseCnt}: No trees.`);
  else if (treeCnt === 1) console.log(`Case ${caseCnt}: There is one tree.`);
  else console.log(`Case ${caseCnt}: A forest of ${treeCnt} trees.`);
}
