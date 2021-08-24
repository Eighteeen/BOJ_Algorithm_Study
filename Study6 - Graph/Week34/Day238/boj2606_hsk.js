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

//// 깔끔하고 변수명이 구체적이어서 더욱 이해하기 쉬웠습니다.
//// numOfComputer는 '컴퓨터의 개수'로 읽힙니다. (number of people: 인원수)
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

const numOfCmputers = Number(input());
const pairOfVertices = Number(input());
const graphOfComputers = Array.from(
  new Array(numOfCmputers + 1),
  () => new Array(),
);

for (let i = 1; i <= pairOfVertices; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  graphOfComputers[vertexA].push(vertexB);
  graphOfComputers[vertexB].push(vertexA);
}

console.log(getCntComputerWithVirus(1));
