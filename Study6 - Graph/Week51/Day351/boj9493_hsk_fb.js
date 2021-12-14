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

  //// 지금도 좋긴한데 가독성이 약간 떨어지는거같아요 : 22 변수 활용하면 좋을 것 같아요
  //// => 문제 풀었다는 생각에 그만 안다듬었다는.. 그런~~ 비하인드~ 반영했어여~~
  const diffHours = Math.floor(diff / HOUR_TO_SECONDS, 2);
  const diffMonutes = matchDigits(Math.floor((diff % HOUR_TO_SECONDS) / MINUTE_TO_SECONDS), 2);
  const diffSeconds = matchDigits((diff % HOUR_TO_SECONDS) % MINUTE_TO_SECONDS, 2);

  console.log(`${diffHours}:${diffMonutes}:${diffSeconds}`);
}
