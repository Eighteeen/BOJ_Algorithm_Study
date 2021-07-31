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

//// 깔끔합니다.
const setNodeAndMaxDistanceFromDFS = (node, distance) => {
  visitedNode[node] = true;

  if (nodeInfo.distance < distance) {
    nodeInfo = { node: node, distance: distance };
  }

  for (let [nextNode, nextDistance] of tree[node]) {
    if (visitedNode[nextNode]) continue;
    setNodeAndMaxDistanceFromDFS(nextNode, distance + nextDistance);
  }
};

const V = parseInt(input());
const tree = Array.from(new Array(V + 1), () => new Array());
//// youAndI.you youAndI.I 이런 느낌입니다
//// 변수명을 포괄적으로 바꾸거나 두 변수를 객체에 담지 않고 개별 변수로 만드는 게 좋을 것 같습니다 : 22
//// => 변경했습니다.
let nodeInfo = { node: 0, distance: 0 };

for (let i = 0; i < V; i++) {
  const [node, ...edgeInfo] = input().split(' ').map(Number);
  for (let j = 0; j < edgeInfo.length - 1; j += 2) {
    tree[node].push([edgeInfo[j], edgeInfo[j + 1]]);
  }
}

//// setNode~ 함수를 두번 호출하는 이유를 설명했다면 더 이해하기 쉬웠을 것 같아요
//// 예를 들면 wsb 코드처럼 maxDiameterEndNode, maxDistance 이름을 각각 붙여 설명하는 식으로요!
//// => 변수명을 바꿔보았지만 변수명을 설정해주지 않는게 더 깔끔하긴 한 것 같네요ㅠㅠㅠ
let visitedNode = new Array(V + 1).fill(false);
setNodeAndMaxDistanceFromDFS(1, 0);

let farthestNodeNum = nodeInfo.node;
visitedNode = new Array(V + 1).fill(false);
setNodeAndMaxDistanceFromDFS(farthestNodeNum, 0);

let maxDiameter = nodeInfo.distance;
console.log(maxDiameter);
