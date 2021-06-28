const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `50
30
24
5
28
45
98
52
60`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

class Node {
  constructor(data) {
    this.data = data;
    this.leftNode = null;
    this.rightNode = null;
  }

  setLeftNode(data) {
    this.leftNode = new Node(data);
  }

  setRightNode(data) {
    this.rightNode = new Node(data);
  }

  insert(num) {
    if (num < this.data) {
      if (this.leftNode === null) this.setLeftNode(num);
      else this.leftNode.insert(num);
    } else {
      if (this.rightNode === null) this.setRightNode(num);
      else this.rightNode.insert(num);
    }
  }
}

const getPostOrder = (node) => {
  if (node === null) return;

  getPostOrder(node.leftNode);
  getPostOrder(node.rightNode);
  postOrder.push(node.data);
};

let postOrder = [];
let root = new Node(parseInt(input()));

for (let i = 1; i < stdin.length; i++) {
  root.insert(parseInt(input()));
}

getPostOrder(root);
console.log(postOrder.join('\n'));
