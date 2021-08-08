const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `19
1 2 3
2 4 5
3 6 7
4 8 -1
5 9 10
6 11 12
7 13 -1
8 -1 -1
9 14 15
10 -1 -1
11 16 -1
12 -1 -1
13 17 -1
14 -1 -1
15 18 -1
16 -1 -1
17 -1 19
18 -1 -1
19 -1 -1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 오.. 변수명 작명 스타일이 확 변하신거같은데 되게 직관적으로 의미가 바로 와닿아요 굳굳
const accumulateRowNumInOrderOfTree = (curNode, level) => {
  let leftChildNode = tree[curNode][0];
  let rightChildNode = tree[curNode][1];

  if (leftChildNode !== -1) accumulateRowNumInOrderOfTree(leftChildNode, level + 1);

  levelListOfNode[level].push(accumulateRowNum++);

  if (rightChildNode !== -1) accumulateRowNumInOrderOfTree(rightChildNode, level + 1);
};

const getWidestLevelAndWidth = () => {
  let widestLevel = 1;
  let widestWidth = 1;

  for (let i = 2; i <= N; i++) {
    if (levelListOfNode[i].length === 0) continue;

    let leastRowNum = Math.min.apply(null, levelListOfNode[i]);
    let biggestRowNum = Math.max.apply(null, levelListOfNode[i]);

    let width = biggestRowNum - leastRowNum + 1;
    if (widestWidth < width) {
      widestLevel = i;
      widestWidth = width;
    }
  }

  return [widestLevel, widestWidth];
};

const N = parseInt(input());
const tree = Array.from(new Array(N + 1), () => new Array());
const ListInOrderTofindRoot = new Array(N + 1).fill(0);
const levelListOfNode = Array.from(new Array(N + 1), () => new Array());

for (let i = 0; i < N; i++) {
  const [parent, left, right] = input().split(' ').map(Number);

  tree[parent].push(left);
  tree[parent].push(right);

  ListInOrderTofindRoot[parent] += 1;
  if (left !== -1) ListInOrderTofindRoot[left] += 1;
  if (right !== -1) ListInOrderTofindRoot[right] += 1;
}

const rootNum = ListInOrderTofindRoot.indexOf(1);

let accumulateRowNum = 1;
accumulateRowNumInOrderOfTree(rootNum, 1);

const [widestLevel, widestWidth] = getWidestLevelAndWidth();
console.log(widestLevel, widestWidth);
