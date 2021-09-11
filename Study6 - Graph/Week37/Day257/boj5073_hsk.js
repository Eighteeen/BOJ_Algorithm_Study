const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `7 7 7
6 5 4
3 2 5
6 2 6
0 0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

while (1) {
  let sides = input().split(' ').map(Number);
  sides.sort((a, b) => a - b);

  let [sideA, sideB, sideC] = sides;

  if (!sideA && !sideB && !sideC) break;

  //// 사소하지만 Scalene의 경우가 Isosceles보다 연산을 더 많이 해야 해서
  //// else if로 Scalene을 연산하고 else로 Isosceles로 푼 것이 조금 아쉽습니다.
  if (sideC >= sideA + sideB) console.log('Invalid');
  else {
    if (sideA === sideB && sideB === sideC) console.log('Equilateral');
    else if (sideA !== sideB && sideA !== sideC && sideB !== sideC) console.log('Scalene');
    else console.log('Isosceles');
  }
}
//// 깔끔 :22