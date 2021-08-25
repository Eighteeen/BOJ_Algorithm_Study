const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1000 1 1000
999 1000`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getVerticesByDFS = (startVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  const stack = [startVertex];
  const dfsList = [];

  while (stack.length) {
    let vertex = stack.pop();

    if (visitedVertices[vertex]) continue;
    visitedVertices[vertex] = true;

    dfsList.push(vertex);

    let sortedVertices = graph[vertex].sort((a, b) => b - a);
    for (let nextVertex of sortedVertices) {
      stack.push(nextVertex);
    }
  }

  return dfsList;
};

const getVerticesByBFS = (startVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  const queue = [startVertex];
  const bfsList = [];

  while (queue.length) {
    let vertex = queue.shift();

    if (visitedVertices[vertex]) continue;
    visitedVertices[vertex] = true;

    bfsList.push(vertex);

    let sortedVertices = graph[vertex].sort((a, b) => a - b);
    for (let nextVertex of sortedVertices) {
      queue.push(nextVertex);
    }
  }

  return bfsList;
};

const [N, M, V] = input().split(' ').map(Number);
const graph = Array.from(new Array(N + 1), () => new Array());
for (let i = 0; i < M; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  graph[vertexA].push(vertexB);
  graph[vertexB].push(vertexA);
}

console.log(getVerticesByDFS(V).join(' '));
console.log(getVerticesByBFS(V).join(' '));
