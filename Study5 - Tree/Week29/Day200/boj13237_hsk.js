const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
-1
1
1
2
2
3
3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 큐를 활용한 방법은 생각도 못 했는데 다양한 방법을 알아가요!
//// 반환형이 없는데 get은 쪼끔 오해의 소지가 있는 것 같습니다 : 22
const getHeightFromBFS = () => {
  while (queueNodeList.length > 0) {
    let size = queueNodeList.length;
    for (let i = 0; i < size; i++) {
      let currNode = queueNodeList.shift();

      tree[currNode].map((node) => {
        if (node) {
          heightList[node] = height + 1;
          queueNodeList.push(node);
        }
      });
    }
    height++;
  }
};

const N = parseInt(input());
const tree = Array.from(Array(N + 1), () => Array(2).fill(null));
const heightList = [];
const queueNodeList = [];
let height = 0;

for (let nodeNum = 1; nodeNum <= N; nodeNum++) {
  const parentNodeNum = parseInt(input());

  if (parentNodeNum === -1) {
    const rootNodeNum = nodeNum;

    heightList[rootNodeNum] = height;
    queueNodeList.push(rootNodeNum);
    continue;
  }

  if (!tree[parentNodeNum][0]) tree[parentNodeNum][0] = nodeNum;
  else tree[parentNodeNum][1] = nodeNum;
}

getHeightFromBFS();
console.log(heightList.splice(1).join('\n'));

//// 무난깔끔합니다~