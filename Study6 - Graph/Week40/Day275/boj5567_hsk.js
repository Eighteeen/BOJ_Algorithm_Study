const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6
5
2 3
3 4
4 5
5 6
2 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// ByBFS는 없어도 될 정보인 것 같고, DistanceArr는 이름의 의미를 알기 어렵습니다
const getDistanceArrByBFS = (startVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  let distance = new Array(N + 1).fill(0);
  let queue = [startVertex];
  let queueCursor = 0;

  while (queue.length > queueCursor) {
    const vertex = queue[queueCursor++];

    visitedVertices[vertex] = true;

    for (let nextVertex of friendshipGraph[vertex]) {
      if (visitedVertices[nextVertex]) continue;
      visitedVertices[nextVertex] = true;

      distance[nextVertex] = distance[vertex] + 1;
      queue.push(nextVertex);
    }
  }

  return distance;
};

const N = parseInt(input());
const M = parseInt(input());
const friendshipGraph = Array.from(new Array(N + 1), () => new Array());
for (let i = 0; i < M; i++) {
  const [friendA, friendB] = input().split(' ').map(Number);

  friendshipGraph[friendA].push(friendB);
  friendshipGraph[friendB].push(friendA);
}

const numOfInvitedToWedding = getDistanceArrByBFS(1).filter((distance) => distance === 1 || distance === 2);
console.log(numOfInvitedToWedding.length);
