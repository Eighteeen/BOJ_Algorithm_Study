const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setNodeAndMaxDistanceFromDFS = (node, distance) => {
  visitedNode[node] = true;

  if (nodeAndMaxDistance.distance < distance) {
    nodeAndMaxDistance = { node: node, distance: distance };
  }

  for (let [nextNode, nextDistance] of tree[node]) {
    if (visitedNode[nextNode]) continue;
    setNodeAndMaxDistanceFromDFS(nextNode, distance + nextDistance);
  }
};

const V = parseInt(input());
const tree = Array.from(new Array(V + 1), () => new Array());
let nodeAndMaxDistance = { node: 0, distance: 0 };

for (let i = 0; i < V; i++) {
  const [node, ...edgeInfo] = input().split(' ').map(Number);
  for (let j = 0; j < edgeInfo.length - 1; j += 2) {
    tree[node].push([edgeInfo[j], edgeInfo[j + 1]]);
  }
}

let visitedNode = new Array(V + 1).fill(false);
setNodeAndMaxDistanceFromDFS(1, 0);

visitedNode = new Array(V + 1).fill(false);
setNodeAndMaxDistanceFromDFS(nodeAndMaxDistance.node, 0);

console.log(nodeAndMaxDistance.distance);
