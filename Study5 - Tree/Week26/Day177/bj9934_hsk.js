const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
2 1 3`
).split('\n');

const K = parseInt(stdin[0]);
const buildingsByInOrder = stdin[1].split(' ').map((item) => parseInt(item));
const buildingsTree = Array.from(Array(K), () => Array().fill());

//// 깔끔합니다
const makeBuildingTree = (tree, idx) => {
  const rootNodeIndex = parseInt(tree.length / 2);
  buildingsTree[idx].push(tree[rootNodeIndex]);

  if (tree.length === 1) return;
  makeBuildingTree(tree.slice(0, rootNodeIndex), idx + 1);
  makeBuildingTree(tree.slice(rootNodeIndex + 1), idx + 1);
};

makeBuildingTree(buildingsByInOrder, 0);

for (let node of buildingsTree) {
  console.log(node.join(' '));
}
