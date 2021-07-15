const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 2
2 3
3 4
4 5
4
1 1
1 2
2 1
2 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());
const nodeInfoInTree = Array.from(Array(N + 1), () => Array());
for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input()
    .split(' ')
    .map((node) => parseInt(node));

  nodeInfoInTree[nodeA].push(nodeB);
  nodeInfoInTree[nodeB].push(nodeA);
}

//// 군더더기 없이 까끔합니다잉 : 22 낄끔
const questionNum = parseInt(input());
const isRightQuestion = [];
for (let q = 1; q <= questionNum; q++) {
  const [t, k] = input()
    .split(' ')
    .map((value) => parseInt(value));

  if (t === 1) {
    const isCutVertex = nodeInfoInTree[k].length === 1 ? 'no' : 'yes';
    isRightQuestion.push(isCutVertex);
  }
  if (t === 2) isRightQuestion.push('yes');
}

console.log(isRightQuestion.join('\n'));
