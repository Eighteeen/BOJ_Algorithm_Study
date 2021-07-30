const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setNodeAndMaxDistanceFromDFS = (node, distance) => {
  visitedNode[node] = true;

  if (nodeAndMaxDistance.distance < distance) {
    nodeAndMaxDistance = { node: node, distance: distance };
  }

  for (let [nextNode, nextDistance] of tree[node]) {
    if (visitedNode[nextNode]) continue;
    setNodeAndMaxDistanceFromDFS(nextNode, distance + nextDistance);
  }
};

const V = parseInt(input());
const tree = Array.from(new Array(V + 1), () => new Array());
//// youAndI.you youAndI.I 이런 느낌입니다
//// 변수명을 포괄적으로 바꾸거나 두 변수를 객체에 담지 않고 개별 변수로 만드는 게 좋을 것 같습니다 
let nodeAndMaxDistance = { node: 0, distance: 0 };

for (let i = 0; i < V; i++) {
  const [node, ...edgeInfo] = input().split(' ').map(Number);
  for (let j = 0; j < edgeInfo.length - 1; j += 2) {
    tree[node].push([edgeInfo[j], edgeInfo[j + 1]]);
  }
}

//// setNode~ 함수를 두번 호출하는 이유를 설명했다면 더 이해하기 쉬웠을 것 같아요
//// 예를 들면 wsb 코드처럼 maxDiameterEndNode, maxDistance 이름을 각각 붙여 설명하는 식으로요!
let visitedNode = new Array(V + 1).fill(false);
setNodeAndMaxDistanceFromDFS(1, 0);

visitedNode = new Array(V + 1).fill(false);
setNodeAndMaxDistanceFromDFS(nodeAndMaxDistance.node, 0);

console.log(nodeAndMaxDistance.distance);
