const fs = require('fs');
const [upperBurger, middleBurger, lowerBugger, coke, cider] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `1999
1999
100
189
100`
)
  .split('\n')
  .map(Number);

const minPriceOfBurger = Math.min(upperBurger, middleBurger, lowerBugger);
const minPriceOfBeverage = Math.min(coke, cider);

console.log(minPriceOfBurger + minPriceOfBeverage - 50);
