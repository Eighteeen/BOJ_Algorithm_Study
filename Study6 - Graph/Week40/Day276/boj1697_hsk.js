const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5 100000`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getLeastTimeToFindSister = (startNum) => {
  const visitedVertices = new Array(N + 1).fill(false);
  const leastTimeToFindSister = [];
  const queue = [startNum];
  let queueCursor = 0;

  leastTimeToFindSister[startNum] = 0;
  visitedVertices[startNum] = true;

  while (queue.length > queueCursor) {
    let vertex = queue[queueCursor++];

    if (vertex === K) break;

    const calcVertex = [vertex - 1, vertex + 1, vertex * 2];
    for (let i = 0; i < 3; i++) {
      let nextVertex = calcVertex[i];

      if (nextVertex < 0 || nextVertex > 100000 || visitedVertices[nextVertex]) continue;
      visitedVertices[nextVertex] = true;

      leastTimeToFindSister[nextVertex] = leastTimeToFindSister[vertex] + 1;
      queue.push(nextVertex);
    }
  }

  return leastTimeToFindSister[K];
};

const [N, K] = input().split(' ').map(Number);
console.log(getLeastTimeToFindSister(N));
