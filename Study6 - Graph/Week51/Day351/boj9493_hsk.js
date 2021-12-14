const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `21 70 80
26 65 80
0 0 0`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const matchDigits = (result, digit) => {
  return new Array(Math.max(digit - result.toString().length, 0) + 1).join('0') + result;
};

const [HOUR_TO_SECONDS, MINUTE_TO_SECONDS] = [3600, 60];
while (1) {
  const [M, A, B] = input().split(' ').map(Number);
  if (M === 0 && A === 0 && B === 0) break;

  const durationOfA = (M / A) * HOUR_TO_SECONDS;
  const durationOfB = (M / B) * HOUR_TO_SECONDS;
  const diff = Math.round(Math.abs(durationOfA - durationOfB));

  //// 지금도 좋긴한데 가독성이 약간 떨어지는거같아요 ㅠ
  console.log(
    `${Math.floor(diff / HOUR_TO_SECONDS, 2)}:${matchDigits(
      Math.floor((diff % HOUR_TO_SECONDS) / MINUTE_TO_SECONDS),
      2
    )}:${matchDigits((diff % HOUR_TO_SECONDS) % MINUTE_TO_SECONDS, 2)}`
  );
}
