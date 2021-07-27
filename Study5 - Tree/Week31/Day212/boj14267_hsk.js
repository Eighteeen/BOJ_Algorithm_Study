const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 3
-1 1 2 3 4
2 2
3 4
5 6`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setDownCompliment = (ceo) => {
  const queueNodeList = [ceo];

  while (queueNodeList.length) {
    let person = queueNodeList.pop();

    for (let nextPerson of superiorTree[person]) {
      complimentList[nextPerson] += complimentList[person];
      queueNodeList.push(nextPerson);
    }
  }
};

const [N, compliment] = input().split(' ').map(Number);
const superiorList = input().split(' ').map(Number);
const superiorTree = Array.from(new Array(N + 1), () => new Array());
const complimentList = new Array(N + 1).fill(0);

for (let i = 2; i <= N; i++) {
  superiorTree[superiorList[i - 1]].push(i);
}

for (let j = 0; j < compliment; j++) {
  const [employeeNum, complimentWeight] = input().split(' ').map(Number);
  complimentList[employeeNum] += complimentWeight;
}

setDownCompliment(1);
console.log(complimentList.slice(1).join(' '));
