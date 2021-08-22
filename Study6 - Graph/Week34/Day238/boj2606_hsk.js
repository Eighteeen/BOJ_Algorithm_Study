const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7
6
1 2
2 3
1 5
5 2
5 6
4 7`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getCntComputerWithVirus = (numOfComputer) => {
  const visitedComputers = new Array(numOfCmputers + 1).fill(false);
  const stackNodeList = [numOfComputer];
  let cntComputerWithVirus = 0;

  while (stackNodeList.length) {
    let computer = stackNodeList.pop();

    if (visitedComputers[computer]) continue;
    visitedComputers[computer] = true;

    cntComputerWithVirus += 1;

    for (let connectedComputer of graphOfComputers[computer]) {
      stackNodeList.push(connectedComputer);
    }
  }

  return cntComputerWithVirus - 1;
};

const numOfCmputers = parseInt(input());
const pairOfComputers = parseInt(input());
const graphOfComputers = Array.from(new Array(numOfCmputers + 1), () => new Array());
for (let i = 1; i <= pairOfComputers; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  graphOfComputers[vertexA].push(vertexB);
  graphOfComputers[vertexB].push(vertexA);
}

console.log(getCntComputerWithVirus(1));
