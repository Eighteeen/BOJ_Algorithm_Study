const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 7
3 6
4 3
3 2
1 3
1 2
2 4
5 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getDistanceFromStartVertex = (startVertex) => {
  const visited = new Array(N + 1).fill(false);
  const distance = new Array(N + 1).fill(0);
  const queue = [startVertex];

  visited[startVertex] = true;

  while (queue.length) {
    const vertex = queue.shift();

    for (let nextVertex of graph[vertex]) {
      if (visited[nextVertex]) continue;
      visited[nextVertex] = true;

      distance[nextVertex] = distance[vertex] + 1;
      queue.push(nextVertex);
    }
  }

  return distance;
};

const [N, M] = input().split(' ').map(Number);
const graph = Array.from(new Array(N + 1), () => new Array());
for (let i = 0; i < M; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  graph[vertexA].push(vertexB);
  graph[vertexB].push(vertexA);
}

let distanceFromOne = getDistanceFromStartVertex(1);

let maxDistance = Math.max.apply(null, distanceFromOne);
let maxDistanceVertexNum = distanceFromOne.indexOf(maxDistance);
let cntOfMaxDistance = distanceFromOne.filter((distance) => distance === maxDistance).length;

//// 깔끔 : 22
console.log(maxDistanceVertexNum, maxDistance, cntOfMaxDistance);
