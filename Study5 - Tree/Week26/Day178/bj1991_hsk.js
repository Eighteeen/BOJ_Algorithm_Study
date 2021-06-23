const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
A B C
B D .
C E F
E . .
F . G
D . .
G . .`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const preOrderTree = (node) => {
  treeList.preOrder.push(node.item);
  if (node.leftNode !== '.') preOrderTree(tree[node.leftNode]);
  if (node.rightNode !== '.') preOrderTree(tree[node.rightNode]);
};

const inOrderTree = (node) => {
  if (node.leftNode !== '.') inOrderTree(tree[node.leftNode]);
  treeList.inOrder.push(node.item);
  if (node.rightNode !== '.') inOrderTree(tree[node.rightNode]);
};

const postOrderTree = (node) => {
  if (node.leftNode !== '.') postOrderTree(tree[node.leftNode]);
  if (node.rightNode !== '.') postOrderTree(tree[node.rightNode]);
  treeList.postOrder.push(node.item);
};

class Node {
  constructor(item, leftNode, rightNode) {
    this.item = item;
    this.leftNode = leftNode;
    this.rightNode = rightNode;
  }
}

const N = parseInt(input());
const tree = {};
let treeList = {
  preOrder: [],
  inOrder: [],
  postOrder: [],
};

for (let i = 0; i < N; i++) {
  const [root, leftNode, rightNode] = input().split(' ');
  tree[root] = new Node(root, leftNode, rightNode);
}

preOrderTree(tree['A']);
inOrderTree(tree['A']);
postOrderTree(tree['A']);

for (let item in treeList) {
  console.log(treeList[item].join(''));
}
