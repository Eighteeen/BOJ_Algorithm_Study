const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9 1
1 2 5
2 3 4
3 4 2
2 5 5
1 6 8
1 7 6
7 8 7
7 9 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getTreeInfoFromGigaNode = (startNode) => {
  const visitedNode = new Array(N + 1).fill(false);
  const stackNodeList = [[startNode, 0]];

  let isAfterGigaNode = false;
  let [pillarLen, maxBranchLen] = [0, 0];

  visitedNode[startNode] = true;
  while (stackNodeList.length) {
    let [node, distance] = stackNodeList.pop();

    if (!isAfterGigaNode) pillarLen = distance;
    else maxBranchLen = Math.max(maxBranchLen, distance);

    const meetGigaNode = !isAfterGigaNode && tree[node].length >= 3;
    if (meetGigaNode) {
      isAfterGigaNode = true;
      distance = 0;
    }

    for (let [nextNode, nextDistance] of tree[node]) {
      if (visitedNode[nextNode]) continue;
      visitedNode[nextNode] = true;

      stackNodeList.push([nextNode, distance + nextDistance]);
    }
  }

  return [pillarLen, maxBranchLen];
};

const [N, R] = input().split(' ').map(Number);
const tree = Array.from(new Array(N + 1), () => new Array());

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB, distance] = input().split(' ').map(Number);
  tree[nodeA].push([nodeB, distance]);
  tree[nodeB].push([nodeA, distance]);
}

tree[0].push([R, 0]);
tree[R].push([0, 0]);

const [pillarLen, maxBranchLen] = getTreeInfoFromGigaNode(0);
console.log(pillarLen, maxBranchLen);
