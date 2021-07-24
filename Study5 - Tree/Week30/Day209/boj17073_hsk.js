const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 20
5 3
3 4
2 1
1 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getLeafNodeCnt = () => {
  let leafNodeCnt = 0;
  for (let i = 2; i <= N; i++) {
    if (tree[i].length === 1) leafNodeCnt++;
  }

  return leafNodeCnt;
};

const [N, amountOfWater] = input().split(' ').map(Number);
const tree = Array.from(new Array(N + 1), () => new Array());
for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);
}

const avgOfWater = amountOfWater / getLeafNodeCnt();
console.log(avgOfWater.toFixed(10));
