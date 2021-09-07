const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `10 3
2 5
1 6
1 3
2 8`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMinTimeToEndVertex = (startVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  let minTimeToVertices = new Array(N + 1).fill(0);

  const queue = [startVertex];
  let queueCursor = 0;

  visitedVertices[startVertex] = true;

  while (queue.length > queueCursor) {
    let vertex = queue[queueCursor++];

    for (let nextVertex of graph[vertex]) {
      if (visitedVertices[nextVertex]) continue;
      visitedVertices[nextVertex] = true;

      minTimeToVertices[nextVertex] = minTimeToVertices[vertex] + 1;
      queue.push(nextVertex);
    }
  }

  return minTimeToVertices[E];
};

const [N, M] = input().split(' ').map(Number);
const [S, E] = input().split(' ').map(Number);
const graph = Array.from(new Array(N + 1), () => new Array());
for (let i = 0; i < M; i++) {
  const [x, y] = input().split(' ').map(Number);

  graph[x].push(y);
  graph[y].push(x);
}

for (let i = 2; i <= N; i++) {
  graph[i].push(i - 1);
  graph[i - 1].push(i);
}

console.log(getMinTimeToEndVertex(S));
