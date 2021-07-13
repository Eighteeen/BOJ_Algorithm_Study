const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 8  5 3  5 2  6 4
5 6  0 0

8 1  7 3  6 2  8 9  7 5
7 4  7 8  7 6  0 0

3 8  6 8  6 4
5 3  5 6  5 2  0 0
-1 -1`
)
  .replace(/\n/gi, '  ')
  .replace(/    /gi, '\n')
  .split('\n');

const getIsTree = (edgeInfoInTree, nodeInfoInTree) => {
  if (edgeInfoInTree.length === nodeInfoInTree.size - 1 || nodeInfoInTree.size === 0) return true;
  else return false;
};

for (let k = 1; k <= stdin.length; k++) {
  let input = stdin[k - 1].split('  ');
  let edgeInfo = input.map((info) => info.split(' ').map((value) => parseInt(value)));

  if (edgeInfo[0][0] < 0 || edgeInfo[0][1] < 0) break;

  let edgeInfoInTree = [];
  let nodeInfoInTree = new Set();
  for (let i = 0; i < edgeInfo.length; i++) {
    const [nodeA, nodeB] = edgeInfo[i];

    if (nodeA === 0 && nodeB === 0) continue;

    edgeInfoInTree.push({
      inEdge: nodeA,
      outEdge: nodeB,
    });
    nodeInfoInTree.add(nodeA);
    nodeInfoInTree.add(nodeB);
  }

  let isTree = getIsTree(edgeInfoInTree, nodeInfoInTree);
  console.log(isTree ? `Case ${k} is a tree.` : `Case ${k} is not a tree.`);
}
