const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
NYY
YNY
YYN`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getCntOfFriends = (startVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  const queue = [[startVertex, 0]];
  let queueCursor = 0;
  let cntOfFriends = 0;

  visitedVertices[startVertex] = true;

  while (queue.length > queueCursor) {
    const [vertex, depth] = queue[queueCursor++];

    if (depth === 2) break;

    for (let i = 0; i < N; i++) {
      if (friendshipGraph[vertex][i] === 'Y' && !visitedVertices[i]) {
        visitedVertices[i] = true;
        cntOfFriends += 1;
        queue.push([i, depth + 1]);
      }
    }
  }

  return cntOfFriends;
};

const N = parseInt(input());
const friendshipGraph = Array.from(Array(N), () => input().split(''));

let maxCntOfFriends = 0;
for (let i = 0; i < N; i++) {
  maxCntOfFriends = Math.max(maxCntOfFriends, getCntOfFriends(i));
}

console.log(maxCntOfFriends);
//// 깔끔 :2
//// 다른 방법이라 흥미로웠어요