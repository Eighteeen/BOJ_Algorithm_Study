const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `14 30
20`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let [hour, minute] = input()
  .split(' ')
  .map((value) => parseInt(value));
let time = parseInt(input());

hour += parseInt(time / 60);
minute += parseInt(time % 60);
hour += parseInt(minute / 60);

console.log(hour % 24, minute % 60);
