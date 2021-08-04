const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
S 100 3
W 50 1
S 10 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getNumOfRescuedSheeps = (curNode) => {
  let numOfRescuedSheeps = Number(treeOfIslands[curNode].cntAnimals);

  for (let nextIsland of treeOfIslands[curNode].connectedIsland) {
    numOfRescuedSheeps += getNumOfRescuedSheeps(nextIsland);
  }

  if (numOfRescuedSheeps < 0) numOfRescuedSheeps = 0;

  return numOfRescuedSheeps;
};

const numOfIslands = parseInt(input());
const treeOfIslands = Array.from(new Array(numOfIslands + 1), () => new Object({ cntAnimals: 0, connectedIsland: [] }));
for (let i = 2; i <= numOfIslands; i++) {
  const [isWolf, numOfAnimals, island] = input().split(' ');

  treeOfIslands[i].cntAnimals = isWolf === 'W' ? -numOfAnimals : numOfAnimals;
  treeOfIslands[island].connectedIsland.push(i);
}

console.log(getNumOfRescuedSheeps(1));
