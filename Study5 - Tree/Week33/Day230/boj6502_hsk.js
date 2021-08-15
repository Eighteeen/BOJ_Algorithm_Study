const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `38 40 60
35 20 70
50 60 80
0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getHypotenuse = (legA, legB) => {
  return Math.sqrt(Math.pow(legA, 2) + Math.pow(legB, 2));
};

//// ㄲㄲ :22
let result = [];
let testCnt = 0;
while (1) {
  testCnt += 1;

  const inputLine = input();
  if (inputLine == 0) break;

  const [radius, width, length] = inputLine.split(' ').map(Number);
  const diameter = 2 * radius;

  if (diameter >= getHypotenuse(width, length)) {
    result.push(`Pizza ${testCnt} fits on the table.`);
  } else {
    result.push(`Pizza ${testCnt} does not fit on the table.`);
  }
}

console.log(result.join('\n'));
