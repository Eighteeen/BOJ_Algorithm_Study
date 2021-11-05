const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `6
2
3
4
3
1
1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getCntOfConnectedVertices = (startVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  const queue = [startVertex];
  let cnt = 0;

  visitedVertices[startVertex] = true;

  while (queue.length) {
    const vertex = queue.shift();

    for (let nextVertex of graph[vertex]) {
      if (!visitedVertices[nextVertex]) {
        visitedVertices[nextVertex] = true;
        queue.push(nextVertex);
        cnt += 1;
      }
    }
  }

  return cnt;
};

const N = parseInt(input());
const graph = Array.from(Array(N + 1), () => Array());
for (let i = 1; i <= N; i++) {
  const numOfGuessSenior = parseInt(input());

  graph[i].push(numOfGuessSenior);
}

let cntOfSeniors = [];
for (let i = 1; i <= N; i++) {
  cntOfSeniors.push(getCntOfConnectedVertices(i));
}

let maxCntOfSenior = Math.max.apply(null, cntOfSeniors);
console.log(cntOfSeniors.findIndex((value) => value === maxCntOfSenior) + 1);
