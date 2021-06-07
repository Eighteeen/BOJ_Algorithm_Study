const fs = require('fs');
let [month, day] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `12 25`)
  .split(' ')
  .map((item) => parseInt(item));

const daysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
const week = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
for (let i = 0; i < month - 1; i++) {
  day += daysOfMonth[i];
}

console.log(week[day % 7]);
