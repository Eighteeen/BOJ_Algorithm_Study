const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9
8 4 9 2 5 1 6 3 7
8 9 4 5 2 6 7 3 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const addPreOrderList = () => {
  const stackNodeList = [[0, N - 1, 0, N - 1]];

  while (stackNodeList.length) {
    const [inStart, inEnd, postStart, postEnd] = stackNodeList.pop();

    if (inStart > inEnd || postStart > postEnd) continue;

    const root = postOrderList[postEnd];
    preOrderList.push(root);

    const rootInderOfInOrder = inOrderList.indexOf(root);
    const leftSize = rootInderOfInOrder - inStart;

    stackNodeList.push([rootInderOfInOrder + 1, inEnd, postStart + leftSize, postEnd - 1]);
    stackNodeList.push([inStart, rootInderOfInOrder - 1, postStart, postStart + leftSize - 1]);
  }
};

const N = parseInt(input());
const inOrderList = input().split(' ').map(Number);
const postOrderList = input().split(' ').map(Number);
const preOrderList = [];

addPreOrderList();
console.log(preOrderList.join(' '));
