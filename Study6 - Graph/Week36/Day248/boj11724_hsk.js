const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const DFS = (startVertex) => {
  const stack = [startVertex];

  visitedVertices[startVertex] = true;

  while (stack.length) {
    const vertex = stack.pop();

    for (let nextVertex of graph[vertex]) {
      if (visitedVertices[nextVertex]) continue;
      visitedVertices[nextVertex] = true;

      stack.push(nextVertex);
    }
  }
};

const [N, M] = input().split(' ').map(Number);
const graph = Array.from(new Array(N + 1), () => new Array());
const visitedVertices = new Array(N + 1).fill(false);
let numOfConnectedElement = 0;

for (let i = 0; i < M; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  graph[vertexA].push(vertexB);
  graph[vertexB].push(vertexA);
}

for (let i = 1; i <= N; i++) {
  ///// 이럴수도 있구나
  if (visitedVertices[i]) continue;

  DFS(i);
  numOfConnectedElement += 1;
}

console.log(numOfConnectedElement);

//// 깔꼼 : 22