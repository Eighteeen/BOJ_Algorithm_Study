const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1 100 1`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const sides = input().split(' ').map(Number);
sides.sort((a, b) => a - b);

const perimeter = sides[0] + sides[1] + Math.min(sides[2], sides[0] + sides[1] - 1);
console.log(perimeter);
