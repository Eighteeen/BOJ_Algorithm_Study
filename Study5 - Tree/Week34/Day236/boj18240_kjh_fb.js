// 문제실패 : 모범답안
// 이런 로직을 전혀 떠올릴 수 없었던게 실패 이유인 것 같음
// 1차원 배열이 아닌 treeByDepth처럼 2차원 배열로 한 상태에서도 idx * 2, idx * 2 + 1이 똑같이 왼쪽 자식과 오른쪽 자식이라는 점이 정말 신기했음

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

  // 왼 자식 -> 자기 자신 -> 오른 자식 순회하며 넘버링
  traversalInOrder(depth + 1, idx * 2);
  sequenceInTree[treeByDepth[depth][idx]] = num++;
  traversalInOrder(depth + 1, idx * 2 + 1);
};

const N = parseInt(input());
const depthArr = input().split(' ').map(Number);
const treeByDepth = Array.from(new Array(N + 1), () => new Array());
let isPossibleSequence = true;
let sequenceInTree = [];

// treeByDepth에 depth별로 '몇번째 AN인지' 값을 추가해둠
treeByDepth[0].push(1);
depthArr.map((depth, idx) => {
  treeByDepth[depth].push(idx + 2);

  // 이미 꽉 찼거나 연결할 부모 노드가 0개면 만들 수 없음
  if (treeByDepth[depth - 1].length * 2 < treeByDepth[depth].length) isPossibleSequence = false;
});

if (isPossibleSequence) {
  traversalInOrder(0, 0);
  // 넘버링 된 것 출력
  console.log(sequenceInTree.slice(1).join(' '));
} else {
  console.log('-1');
}
