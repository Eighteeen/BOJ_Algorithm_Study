const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
7 6
1 2 1
2 3 4
2 4 1
5 1 4
6 5 1
7 5 2
7 6
1 2 1
2 3 4
2 4 1
5 1 4
6 5 1
7 5 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 무난 깔끔
const getMinNumOfDynamite = (curNode, dynamite) => {
  let minNumOfDynamite = 0;

  for (let node of tree[curNode]) {
    let nextNode = node.connectedNode;
    let nextDynamite = node.numOfDynamite;

    if (visitedNodes[nextNode]) continue;
    visitedNodes[nextNode] = true;

    minNumOfDynamite += getMinNumOfDynamite(nextNode, nextDynamite);
  }

  if (!minNumOfDynamite) return dynamite === Number.MAX_SAFE_INTEGER ? 0 : dynamite;
  return Math.min(minNumOfDynamite, dynamite);
};

const T = parseInt(input());
let tree = [];
let visitedNodes = [];
let result = [];

for (let i = 0; i < T; i++) {
  const [numOfIslands, numOfBridges] = input().split(' ').map(Number);
  tree = Array.from(new Array(numOfIslands + 1), () => new Array());
  visitedNodes = new Array(numOfIslands + 1).fill(false);

  for (let j = 1; j < numOfIslands; j++) {
    //// D 대신 numOfDynamite 했으면 58~59줄에서 이름만 써도 돼서 까리했을 것 같아요 ㅎㅋ
    const [nodeA, nodeB, D] = input().split(' ').map(Number);

    tree[nodeA].push({ connectedNode: nodeB, numOfDynamite: D });
    tree[nodeB].push({ connectedNode: nodeA, numOfDynamite: D });
  }

  visitedNodes[1] = true;
  result.push(getMinNumOfDynamite(1, Number.MAX_SAFE_INTEGER));
}
console.log(result.join('\n'));
