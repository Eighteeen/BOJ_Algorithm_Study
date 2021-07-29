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

    for (let nextPerson of superiorAndJuniorTree[person]) {
      complimentList[nextPerson] += complimentList[person];
      queueNodeList.push(nextPerson);
    }
  }
};

const [N, compliment] = input().split(' ').map(Number);
const superiorList = input().split(' ').map(Number);
//// 위의 superiorList에서도 상사의 정보만 있어서 superiorTree에서도 상사에 관련한 정보만 있다고 생각이 들어서 다시 생각해야 했어요.
//// junior, underling 등의 부하에 관련한 용어가 쓰인다면 변수명에 대한 이해가 더 쉽게 될 것 같습니다!
//// : 넹 변경하겠습니다~
const superiorAndJuniorTree = Array.from(new Array(N + 1), () => new Array());
const complimentList = new Array(N + 1).fill(0);

for (let i = 2; i <= N; i++) {
  superiorAndJuniorTree[superiorList[i - 1]].push(i);
}

for (let j = 0; j < compliment; j++) {
  const [employeeNum, complimentWeight] = input().split(' ').map(Number);
  complimentList[employeeNum] += complimentWeight;
}

setDownCompliment(1);
console.log(complimentList.slice(1).join(' '));

//// 무난쓰합니다 : 22
