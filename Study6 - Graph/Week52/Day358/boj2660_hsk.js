const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 2
2 3
3 4
4 5
2 4
5 3
-1 -1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 올 걍 이름 이렇게 지어도 괜찮겠네요
const getMaxDistance = (startVertex) => {
  const distance = new Array(numOfMember + 1).fill(0);
  const visitedVertices = new Array(numOfMember + 1).fill(false);
  let queue = [startVertex];
  let queueCursor = 0;
  visitedVertices[startVertex] = true;

  while (queue.length > queueCursor) {
    const vertex = queue[queueCursor++];
    for (let nextVertex of graphOfFriendship[vertex]) {
      if (visitedVertices[nextVertex]) continue;

      visitedVertices[nextVertex] = true;
      queue.push(nextVertex);
      distance[nextVertex] = distance[vertex] + 1;
    }
  }

  return Math.max.apply(null, distance);
};

const numOfMember = parseInt(input());
const graphOfFriendship = Array.from(Array(numOfMember + 1), () => new Array());
while (1) {
  const [vertexA, vertexB] = input().split(' ').map(Number);
  if (vertexA === -1 && vertexB === -1) break;

  graphOfFriendship[vertexA].push(vertexB);
  graphOfFriendship[vertexB].push(vertexA);
}

let candidateOfChairman = [];
let pointOfChairman = 51;
for (let i = 1; i <= numOfMember; i++) {
  let pointOfCandidate = getMaxDistance(i);
  if (pointOfCandidate === pointOfChairman) {
    candidateOfChairman.push(i);
  } else if (pointOfCandidate < pointOfChairman) {
    candidateOfChairman = [i];
    pointOfChairman = pointOfCandidate;
  }
}

console.log(pointOfChairman, candidateOfChairman.length);
console.log(...candidateOfChairman);
//// 깔끔