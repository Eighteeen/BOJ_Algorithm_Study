const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1 1 1 1 1 1 1 1 1 2
5 5 5 5 5 5 5 5 5 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

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
    if (is_used_buckets[barn_location][i] === 0) {
      is_used_buckets[barn_location][i] = 1;

      let change_barn_amount = buckets[barn_location][i] * isOddDay(barn_location);
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
