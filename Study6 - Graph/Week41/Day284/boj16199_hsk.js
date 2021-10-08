const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2005 1 2
2007 1 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 조건문들을 충분히 줄일 수 있을 것 같습니다.
const getManAge = () => {
  let manAge = 0;

  if (BIRTH_MONTH < STANDARD_MONTH) {
    manAge = age;
  } else if (BIRTH_MONTH === STANDARD_MONTH) {
    if (BIRTH_DAY <= STANDARD_DAY) {
      manAge = age;
    } else {
      manAge = age - 1;
    }
  } else {
    manAge = age - 1;
  }

  return manAge;
};

const [BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY] = input().split(' ').map(Number);
const [STANDARD_YEAR, STANDARD_MONTH, STANDARD_DAY] = input().split(' ').map(Number);
const age = STANDARD_YEAR - BIRTH_YEAR;

const [manAge, countAge, yearAge] = [getManAge(), age + 1, age];
console.log(manAge + '\n' + countAge + '\n' + yearAge);

//// 깔끔