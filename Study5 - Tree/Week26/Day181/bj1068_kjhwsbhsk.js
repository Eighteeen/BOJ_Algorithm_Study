const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9
-1 0 0 2 2 4 4 6 6
4`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const parentList = input()
  .split(' ')
  .map((item) => parseInt(item));
const deleteNodeNum = parseInt(input());
const tree = Array.from(Array(N), () => Array());

const getCntLeafNode = (nodeNum) => {
  let childNodeList = tree[nodeNum];
  if (childNodeList.length === 0) return 1;

  let count = 0;
  childNodeList.forEach((childNodeNum) => {
    count += getCntLeafNode(childNodeNum);
  });

  return count;
};

const solve = () => {
  let rootIndex = parentList.indexOf(-1);
  if (rootIndex === deleteNodeNum) return 0;

  for (let i = 0; i < N; i++) {
    if (i === deleteNodeNum || i === rootIndex) continue;
    tree[parentList[i]].push(i);
  }
  return getCntLeafNode(rootIndex);
};
``;
console.log(solve());
