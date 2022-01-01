const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `3
3
0 1 0
1 0 1
0 1 0
1 2 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const setIsVisitedCountries = (startCountry) => {
  const queue = [startCountry];
  visitedCountries[startCountry] = true;

  while (queue.length) {
    const country = queue.shift();

    linkedCountryInfo[country].forEach((nextCountry, countryIdx) => {
      if (!visitedCountries[countryIdx] && nextCountry) {
        visitedCountries[countryIdx] = true;
        queue.push(countryIdx);
      }
    });
  }
};

const getIsVisitedAllCountries = () => {
  for (let country of travelPlan) {
    if (!visitedCountries[country - 1]) {
      return false;
    }
  }
  return true;
};

const N = parseInt(input());
const M = parseInt(input());
const linkedCountryInfo = Array.from(new Array(N), () => input().split(' ').map(Number));
const travelPlan = input().split(' ').map(Number);

const visitedCountries = new Array(N).fill(false);
const startCountry = travelPlan[0] - 1;
setIsVisitedCountries(startCountry);

console.log(getIsVisitedAllCountries() ? 'YES' : 'NO');
//// 간단하게 구현하신 것 같아요 깔끔 :2