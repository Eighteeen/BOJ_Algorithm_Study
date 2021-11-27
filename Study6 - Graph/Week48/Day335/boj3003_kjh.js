const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let requiredEachPieces = [1, 1, 2, 2, 2, 8];
let eachPieces = input().split(' ').map(x => parseInt(x));

let result = [];
for (let i = 0; i < 6; i++) {
  result.push(requiredEachPieces[i] - eachPieces[i]);
}

console.log(result.join(' '));