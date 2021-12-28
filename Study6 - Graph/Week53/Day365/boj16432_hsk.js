const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
3 7 8 9
1 1
1 1`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const printRiceCakesForTiger = (day, riceCakeEatenYesterday, eatenRiceCakes) => {
  if (day === N) {
    console.log(eatenRiceCakes.split('').join('\n'));
    process.exit();
  }

  for (let riceCake of daysToSellRiceCakes[day]) {
    if (riceCake !== riceCakeEatenYesterday && !checkingIfEatenRiceCake[day][riceCake]) {
      checkingIfEatenRiceCake[day][riceCake] = true;
      printRiceCakesForTiger(day + 1, riceCake, eatenRiceCakes + [riceCake]);
    }
  }
};

const N = parseInt(input());
const daysToSellRiceCakes = [];
const checkingIfEatenRiceCake = Array.from(new Array(N + 1), () => new Array(10).fill(false));
for (let i = 1; i <= N; i++) {
  const [numOfRiceCake, ...typeOfRiceCakes] = input().split(' ').map(Number);
  daysToSellRiceCakes.push(typeOfRiceCakes);
}

printRiceCakesForTiger(0, 0, []);
console.log(-1);
//// 깔끔 : 22
//// console.log(-1); 실행 안 되는 거 넘 신기