const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 1 1 1 1 1 1 1 1 2
5 5 5 5 5 5 5 5 5 5`
).split('\n');
//// 전체적으로 깔끔하지만 비트마스킹의 특징이 크게 쓰인 건 아니라서 살짝 아쉽네요.

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// true false를 반환하게 하고, 35줄쪽에서 삼항연산자로 1 또는 -1 연산하게 하는건 어떨까요
//// boolean을 반환하게 생긴 함수가 1 또는 -1를 반환하는 게 조금 걸리네용
const isOddDay = (day) => {
  if (day) return 1;
  return -1;
};

const backAndForth = (first_barn_amount_milk, day) => {
  if (day === 4) {
    barns_amounts.push(first_barn_amount_milk);
    return;
  }

  if (day + 2 <= 4) backAndForth(first_barn_amount_milk, day + 2);

  let barn_location = day & 1;

  for (let i = 0; i < 10; i++) {
    //// js에선 boolean 배열을 다룰 수 없나요??
    if (is_used_buckets[barn_location][i] === 0) {
      is_used_buckets[barn_location][i] = 1;

      let change_barn_amount = buckets[barn_location][i] * isOddDay(barn_location);
      //// 첫 헛간 우유만 다루는 방법도 있네요. 배워갑니다
      backAndForth(first_barn_amount_milk + change_barn_amount, day + 1);

      is_used_buckets[barn_location][i] = 0;
    }
  }
};

const buckets = [];
let barns_amounts = [];
const is_used_buckets = Array.from(Array(2), () => Array(10).fill(0));

for (let i = 0; i < 2; i++) {
  buckets.push(input().split(' '));
}

backAndForth(0, 0);
let set_overlay_amounts = new Set(barns_amounts);
console.log(set_overlay_amounts.size);
