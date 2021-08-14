const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `AMBULANCE
Evian
madam, i'm adam
***`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// ㄲㄲ
let result = [];
while (1) {
  let str = input();

  if (str === '***') break;

  let reversedStr = str.split('').reverse().join('');
  result.push(reversedStr);
}

console.log(result.join('\n'));
