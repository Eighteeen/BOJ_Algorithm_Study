const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `2
36 77 85
40 55 69`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const TEST_CASE = parseInt(input());
for (let i = 1; i <= TEST_CASE; i++) {
  const sides = input().split(' ').map(Number);
  sides.sort((a, b) => a - b);

  //// hypotenuse빗변 이욜 : 22 이해하기 더 쉽네요
  const [sideA, sideB, hypotenuse] = sides;

  let isrightTriangle = Math.pow(hypotenuse, 2) === Math.pow(sideA, 2) + Math.pow(sideB, 2);

  console.log(`Scenario #${i}:`);
  isrightTriangle ? console.log('yes\n') : console.log('no\n');
}
//// 깔끔 : 22