const fs = require('fs');
let [month, day] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `12 25`)
  .split(' ')
  .map((item) => parseInt(item));

//// 오올 전 Date만 생각했는데
//// 더욱 효율적이네요 굳굳
//// 오호 day를 직접 더하는 방법은 생각 못 한 거 같아요! 재밌는 풀이네요!
//// => 칭찬 감사합니다~
const daysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
const week = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
for (let i = 0; i < month - 1; i++) {
  day += daysOfMonth[i];
}

console.log(week[day % 7]);
