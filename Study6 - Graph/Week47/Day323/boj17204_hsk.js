const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6 2
1
3
5
4
0
2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getLeastDistances = (startVertex) => {
  const visitedVertices = new Array(N).fill(false);
  const queue = [startVertex];
  let distance = 0;

  while (queue.length) {
    const vertex = queue.shift();

    if (vertex === K) return distance;

    for (let nextVertex of graph[vertex]) {
      if (visitedVertices[nextVertex]) continue;
      visitedVertices[nextVertex] = true;
      queue.push(nextVertex);
      distance += 1;
    }
  }

  return -1;
};

const [N, K] = input().split(' ').map(Number);
const graph = Array.from(new Array(N), () => new Array());
for (let i = 0; i < N; i++) {
  const vertex = Number(input());
  graph[i].push(vertex);
}

console.log(getLeastDistances(0));
//// 굿굿 : 22 깔끔!