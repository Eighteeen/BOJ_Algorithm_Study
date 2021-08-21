const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9
1 2 2 1 3 2 3 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let num = 1;
const traversalInOrder = (depth, idx) => {
  if (idx >= treeByDepth[depth].length) return;

  traversalInOrder(depth + 1, idx * 2);
  sequenceInTree[treeByDepth[depth][idx]] = num++;
  traversalInOrder(depth + 1, idx * 2 + 1);
};

const N = parseInt(input());
const depthArr = input().split(' ').map(Number);
const treeByDepth = Array.from(new Array(N + 1), () => new Array());
let isPossibleSequence = true;
let sequenceInTree = [];

treeByDepth[0].push(1);
depthArr.map((depth, idx) => {
  treeByDepth[depth].push(idx + 2);

  if (treeByDepth[depth - 1].length * 2 < treeByDepth[depth].length) isPossibleSequence = false;
});

if (isPossibleSequence) {
  traversalInOrder(0, 0);
  console.log(sequenceInTree.slice(1).join(' '));
} else {
  console.log('-1');
}
