const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
3
911
97625999
91125426
5
113
12340
123440
12345
98346`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const skipInput = (lines) => {
  for (let i = 0; i < lines; i++) {
    input();
  }
};

class Node {
  constructor(str) {
    this.str = str;
    this.children = [];
    this.isLastChar = false;
  }

  markLastChar() {
    this.isLastChar = true;
  }

  addChild(child) {
    this.children.push(child);
  }
}

const isOverlapOfSetTrie = (root, idx, phoneNumList) => {
  const checkNode = phoneNumList[idx];
  if (!checkNode) return true;

  let isAlreadyChild = false;
  let nextNode = null;
  
  for (let node of root.children) {
    if (node.str === checkNode.str) {
      isAlreadyChild = true;
      if (node.isLastChar || checkNode.isLastChar) return false;

      nextNode = node;
      break;
    }
  }

  if (!isAlreadyChild) {
    root.addChild(checkNode);
    nextNode = checkNode;
  }

  return isOverlapOfSetTrie(nextNode, idx + 1, phoneNumList);
};

const T = parseInt(input());
const result = [];
for (let i = 0; i < T; i++) {
  const phoneNumberCnt = parseInt(input());
  const rootNode = new Node(-1);
  let isConsistency = true;

  for (let j = 0; j < phoneNumberCnt; j++) {
    const phoneNumList = input()
      .split('')
      .map((str) => new Node(str));

    phoneNumList[phoneNumList.length - 1].markLastChar();

    if (isOverlapOfSetTrie(rootNode, 0, phoneNumList)) continue;
    
    isConsistency = false;
    skipInput(phoneNumberCnt - j - 1);
    break;
  }
  result.push(isConsistency ? 'YES' : 'NO');
}

console.log(result.join('\n'));
