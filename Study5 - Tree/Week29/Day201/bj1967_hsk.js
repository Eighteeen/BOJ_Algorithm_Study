const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `12
1 2 3
1 3 2
2 4 5
3 5 11
3 6 9
4 7 1
4 8 7
5 9 15
5 10 4
6 11 6
6 12 10`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getNodeAndMaxDistanceFromBFS = (startNode) => {
  const visitedNode = new Array(N + 1).fill(false);
  const queueNodeList = [];
  queueNodeList.push([startNode, 0]);

  let nodeAndMaxDistance = { node: 0, distance: 0 };
  while (queueNodeList.length > 0) {
    const [node, distance] = queueNodeList.shift();

    if (visitedNode[node]) continue;
    visitedNode[node] = true;

    if (nodeAndMaxDistance.distance < distance) {
      nodeAndMaxDistance = { node: node, distance: distance };
    }

    for (let [nextNode, nextDistance] of tree[node]) {
      queueNodeList.push([nextNode, distance + nextDistance]);
    }
  }

  return nodeAndMaxDistance;
};

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => new Array());
for (let i = 1; i < N; i++) {
  const [nodeA, nodeB, distance] = input()
    .split(' ')
    .map((value) => Number(value));
  tree[nodeA].push([nodeB, distance]);
  tree[nodeB].push([nodeA, distance]);
}

const getNodeNumFromMaxDistance = getNodeAndMaxDistanceFromBFS(1).node;
const getMaxDistance = getNodeAndMaxDistanceFromBFS(getNodeNumFromMaxDistance).distance;
console.log(getMaxDistance);
