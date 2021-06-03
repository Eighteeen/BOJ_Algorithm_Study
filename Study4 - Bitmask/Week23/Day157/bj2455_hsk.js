const fs = require('fs');
const input = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `0 32
3 13
28 25
39 0`
).split('\n');

let currentPassenger = 0;
let maxPassenger = 0;


//// 깔꼼 : 22
for (let i = 0; i < 4; i++) {
  const [getOffPassenger, getOnPassenger] = input[i].split(' ').map((item) => parseInt(item));
  currentPassenger += getOnPassenger;
  currentPassenger -= getOffPassenger;
  maxPassenger = Math.max(currentPassenger, maxPassenger);
}
console.log(maxPassenger);
