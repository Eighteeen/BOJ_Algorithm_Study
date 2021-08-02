//  nodejs로 stack size exceeded 문제로 실패
const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 1 1
1 2
2 3
2 4
3 5
5 6`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const calcMaxDepthAbleToDeliver = (curNode) => {
  let maxDepth = 0;

  for (let nextNode of tree[curNode]) {
    if (visitedNodes[nextNode]) continue;
    visitedNodes[nextNode] = true;

    maxDepth = Math.max(maxDepth, calcMaxDepthAbleToDeliver(nextNode) + 1);
  }

  maxDepthOfNodeList[curNode] = maxDepth;
  return maxDepth;
};

const getTotalDistanceToMove = () => {
  let distance = 0;

  for (let i = 1; i <= N; i++) {
    if (maxDepthOfNodeList[i] < D || i === S) continue;

    distance += 1;
  }

  return distance * 2;
};

const [N, S, D] = input().split(' ').map(Number);
const tree = Array.from(new Array(N + 1), () => new Array());
const visitedNodes = new Array(N + 1).fill(false);
const maxDepthOfNodeList = new Array(N + 1).fill(0);

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

visitedNodes[S] = true;
calcMaxDepthAbleToDeliver(S);
console.log(getTotalDistanceToMove());
