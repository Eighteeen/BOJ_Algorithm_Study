const fs = require('fs');
let stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
13
58
120
5056`
).split('\n');

//// 무난 깔끔! :22
const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(input());

const isPalindrome = (num) => {
  for (let i = 0; i < num.length / 2; i++) {
    if (num[i] !== num[num.length - 1 - i]) {
      return false;
    }
  }
  return true;
};

for (let i = 0; i < N; i++) {
  const originNum = input();
  const reverseNum = originNum.split('').reverse().join('');
  const sum = parseInt(originNum) + parseInt(reverseNum);

  console.log(isPalindrome(sum.toString().split('')) ? 'YES' : 'NO');
}
