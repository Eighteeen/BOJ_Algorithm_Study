const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 2
1 3
2 4
2 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const startDFSOnNotLeafNode = () => {
  for (let i = 1; i <= N; i++) {
    if (degreeCntOfNodeList[i] === 1) continue;

    checkIsOddByDFS(i);
    break;
  }
};

//// 코딩 관례에 따라 함수 첫 단어는 동사였으면 합니다!
//// => 넹
//// checkedIsOddByDFS 에서 calcOddOrEvenFromLeafNode 의 작업을 충분히 할 수 있을 것 같아요.
//// => 그렇지만 한눈에 이해하기 편하도록 지금 코드 유지하겠습니다.
const checkIsOddByDFS = (node) => {
  const stackNodeList = [node];
  checkedIsOdd[node] = true;

  while (stackNodeList.length) {
    const node = stackNodeList.pop();

    for (let nextNode of tree[node]) {
      if (checkedIsOdd[nextNode]) continue;
      checkedIsOdd[nextNode] = checkedIsOdd[node] ? false : true;
      stackNodeList.push(nextNode);
    }
  }
};

const calcOddOrEvenFromLeafNode = () => {
  for (let i = 1; i <= N; i++) {
    if (degreeCntOfNodeList[i] !== 1) continue;

    leafNodeCnt++;
    if (checkedIsOdd[i]) odd += 1;
    else even += 1;
  }
};

const N = parseInt(input());
const tree = Array.from(new Array(N + 1), () => new Array());
const degreeCntOfNodeList = new Array(N + 1).fill(0);
const checkedIsOdd = new Array(N + 1).fill(false);
let [odd, even, leafNodeCnt] = [0, 0, 0];

for (let i = 1; i < N; i++) {
  const [nodeA, nodeB] = input().split(' ').map(Number);

  tree[nodeA].push(nodeB);
  tree[nodeB].push(nodeA);

  degreeCntOfNodeList[nodeA]++;
  degreeCntOfNodeList[nodeB]++;
}

//// true false 를 사용해서 푸는 방법을 생각하지 못 했어요. 이번 문제 hsk, kjh 코드와 비교해서 저의 풀이 정-말 복잡해보이네요. ㅠㅠ
startDFSOnNotLeafNode();
//// count 으때요
/// => 그냥 놔둘래영ㅎㅎ
calcOddOrEvenFromLeafNode();

if (N === 1) {
  console.log(1);
} else if (N === 2) {
  console.log(0);
} else if (leafNodeCnt === N - 1) {
  console.log(N - 2);
} else {
  console.log(Math.max(odd, even));
}
