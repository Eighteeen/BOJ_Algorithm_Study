const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getDegreeOfKinshipByBFS = (startVertex) => {
  const visited = new Array(N + 1).fill(false);
  const queue = [[startVertex, 0]];

  while (queue.length) {
    const [vertex, degree] = queue.shift();

    if (vertex === targetVertexB) return degree;

    for (let nextVertex of familyTree[vertex]) {
      if (visited[nextVertex]) continue;
      visited[nextVertex] = true;

      queue.push([nextVertex, degree + 1]);
    }
  }

  return -1;
};

const N = parseInt(input());
const [targetVertexA, targetVertexB] = input().split(' ').map(Number);
const numOfRelationship = parseInt(input());
const familyTree = Array.from(new Array(N + 1), () => new Array());

for (let i = 0; i < numOfRelationship; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  familyTree[vertexA].push(vertexB);
  familyTree[vertexB].push(vertexA);
}

console.log(getDegreeOfKinshipByBFS(targetVertexA));
