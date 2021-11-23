const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5 20
99 101 1000 0 97`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [L, P] = input().split(' ').map(Number);
const numsOfParticipantsInArticle = input().split(' ').map(Number);
const AccurateParticipants = L * P;

let diffParticipantsInArticle = numsOfParticipantsInArticle.map((value) => value - AccurateParticipants);
console.log(...diffParticipantsInArticle);
