const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
16
1 14
8 5
10 16
5 9
4 6
8 4
4 10
1 13
6 15
10 11
6 7
10 2
16 3
8 1
16 12
16 7
5
2 3
3 4
3 1
1 5
3 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const addNodeUntilParentIsRoot = (parentsNodeList, targetNode, ascendToRootArr) => {
  while (parentsNodeList[targetNode]) {
    let parentNode = parentsNodeList[targetNode];

    ascendToRootArr.push(parentNode);
    targetNode = parentNode;
  }

  return ascendToRootArr;
};

const getLCAWithParentNodeFromRoot = (parentsListA, parentsListB) => {
  let depthOfNodeA = parentsListA.length - 1;
  let depthOfNodeB = parentsListB.length - 1;

  while (parentsListA[depthOfNodeA] === parentsListB[depthOfNodeB]) {
    depthOfNodeA--;
    depthOfNodeB--;
  }

  return parentsListA[depthOfNodeA + 1];
};

const T = parseInt(input());
const result = [];

for (let i = 0; i < T; i++) {
  const N = parseInt(input());
  const parentsNodeList = new Array(N + 1);

  for (let j = 1; j < N; j++) {
    const [parent, child] = input().split(' ');

    parentsNodeList[child] = parent;
  }

  let [targetNodeA, targetNodeB] = input().split(' ');
  let ascendToRootOfNodeA = [targetNodeA];
  let ascendToRootOfNodeB = [targetNodeB];

  ascendToRootOfNodeA = addNodeUntilParentIsRoot(parentsNodeList, targetNodeA, ascendToRootOfNodeA);
  ascendToRootOfNodeB = addNodeUntilParentIsRoot(parentsNodeList, targetNodeB, ascendToRootOfNodeB);

  const LCA = getLCAWithParentNodeFromRoot(ascendToRootOfNodeA, ascendToRootOfNodeB);
  result.push(LCA);
}

console.log(result.join('\n'));
