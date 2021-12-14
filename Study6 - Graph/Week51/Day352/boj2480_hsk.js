const fs = require('fs');
const dice = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `6 2 5`)
  .split(' ')
  .map(Number);

const calcPrizeMoney = (value1, value2, value3) => {
  if (value1 === value2 && value1 === value3) {
    return 10000 + value1 * 1000;
  } else if (value1 === value2) {
    return 1000 + 100 * value1;
  } else if (value2 === value3) {
    return 1000 + 100 * value2;
  } else if (value3 === value1) {
    return 1000 + 100 * value3;
  } else {
    return Math.max(value1, value2, value3) * 100;
  }
};

console.log(calcPrizeMoney(...dice));
