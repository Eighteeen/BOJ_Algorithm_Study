const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 4
3 1
3 2
4 3
5 3
`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const cntHackingComputerByBFS = (startVertex) => {
  const hackedComputer = new Array(N + 1).fill(false);
  const queue = [startVertex];

  hackedComputer[startVertex] = true;

  while (queue.length) {
    const vertex = queue.shift();

    for (let nextVertex of computerGraph[vertex]) {
      if (hackedComputer[nextVertex]) continue;

      hackedComputer[nextVertex] = true;
      queue.push(nextVertex);
    }
  }

  return hackedComputer.filter((hacked) => hacked === true).length;
};

const getMaxHackingComputerListByASC = () => {
  let maxCntOfHacking = Math.max.apply(null, cntOfhackingInComputer);
  let maxHackingComputerList = [];
  for (let i = 1; i <= N; i++) {
    if (maxCntOfHacking === cntOfhackingInComputer[i]) maxHackingComputerList.push(i);
  }

  return maxHackingComputerList;
};

const [N, M] = input().split(' ').map(Number);
const computerGraph = Array.from(new Array(N + 1), () => new Array());
const cntOfhackingInComputer = new Array(N + 1).fill(0);

for (let i = 0; i < M; i++) {
  const [fromVertex, toVertex] = input().split(' ').map(Number);

  computerGraph[toVertex].push(fromVertex);
}

for (let i = 1; i <= N; i++) {
  cntOfhackingInComputer[i] = cntHackingComputerByBFS(i);
}

console.log(getMaxHackingComputerListByASC().join(' '));
